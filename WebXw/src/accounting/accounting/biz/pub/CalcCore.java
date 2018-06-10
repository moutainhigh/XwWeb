package accounting.biz.pub;

class CalcCore {

	private NStack n;

	private OStack o;

	private NumberWrapper result;

	private String src;

	public CalcCore(String src) throws Exception {

		this.src = src;

		rebuildString();

		this.n = new NStack();

		this.o = new OStack();

		this.split();

		this.calc();

	}

	public String toString() {

		return src;

	}

	private void rebuildString() {

		// (...)(...) --> (...)*(...)

		src = src.replaceAll("\\)\\(", ")" + OStack.MULTIPLY + "(");

		// 1234(...) --> 1234*(...)

		// (...)1234 --> (...)*1234

		int i = 0;

		while (i < src.length()) {

			if (hasNext(i + 1) && this.isNumber(i)
					&& src.charAt(i + 1) == OStack.LB ||

					hasNext(i + 1) && this.isNumber(i + 1)
					&& src.charAt(i) == OStack.RB) {

				src = src.substring(0, i + 1) + OStack.MULTIPLY
						+ src.substring(i + 1);

			}

			++i;

		}

		// ~1234 -->2~1234

		// (~1234) -->(2~1234)

		i = 0;

		while (i < src.length()) {

			if (src.charAt(i) == OStack.EVOLUTION && pio(i)) {

				src = src.substring(0, i) + "2" + src.substring(i);

			}

			++i;

		}

	}

	private void calc() throws Exception {

		for (int i = 0; i < o.size(); i++) {

			char ch = o.get(i);

			if (ch == OStack.EVOLUTION || ch == OStack.POWER) {

				NumberWrapper n0 = n.remove(i);

				NumberWrapper n1 = n.remove(i);

				ch = o.remove(i);

				NumberWrapper rs;

				if (ch == OStack.EVOLUTION)

					rs = n0.calc(n1, ch);

				else

					rs = n1.calc(n0, ch);

				n.insert(i, rs);

				--i;

			}

		}

		for (int i = o.size() - 1; i >= 0; i--) {

			char ch = o.get(i);

			if (ch == OStack.MULTIPLY || ch == OStack.DIVIDE) {

				NumberWrapper n0 = n.remove(i + 1);

				NumberWrapper n1 = n.remove(i);

				ch = o.remove(i);

				NumberWrapper rs = n0.calc(n1, ch);

				n.insert(i, rs);

			}

		}

		for (int i = o.size() - 1; i >= 0; i--) {

			char ch = o.get(i);

			NumberWrapper n0 = n.remove(i + 1);

			NumberWrapper n1 = n.remove(i);

			ch = o.remove(i);

			NumberWrapper rs = n0.calc(n1, ch);

			n.insert(i, rs);

		}

		if (n.isEmpty() || n.size() > 1)

			result = NumberWrapper.NOTHING;

		else

			result = n.pop();

	}

	public NumberWrapper getResult() {

		return result;

	}

	private void split() throws Exception {

		int cont;

		for (int i = 0; i < src.length(); i++) {

			char c = src.charAt(i);

			switch (c) {

			case '(':

				int pair = nextPair(src, i + 1);

				String sub = substring(i + 1, pair);

				n.push(Calc.doCalc(sub));

				i = pair;

				break;

			case '-':

			case '+':

				boolean iso = pio(i);

				cont = continuous(i + 1);

				if (iso) {

					n.push(new NumberWrapper(parse(substring(i, cont))));

					i = cont - 1;

					break;

				}

			case '*':

			case '/':

			case '%':

			case '^':

			case '~':
				o.push(c);
				break;

			default:

				cont = continuous(i + 1);

				n.push(new NumberWrapper(parse(substring(i, cont))));

				i = cont - 1;

			}

		}

	}

	private double parse(String s) {

		try {

			return Double.parseDouble(s);

		} catch (Exception e) {
		}

		return Double.NaN;

	}

	private String substring(int i, int cont) {

		return src.substring(i, cont);

	}

	private boolean hasNext(int i) {

		return src.length() > i;

	}

	private int continuous(int i) {

		while (hasNext(i) && isNumber(i))

			++i;

		return i;

	}

	private boolean pio(int i) {

		return i < 1 ? true : OStack.iso(src.charAt(i - 1));

	}

	public boolean isNumber(int pos) {

		char c = src.charAt(pos);

		return c <= '9' && c >= '0' || c == '.';

	}

	public int nextPair(String src, int pos) {

		int inner = 0;

		int len = src.length();

		for (int i = pos; i < len; i++) {

			char c = src.charAt(i);

			if (c == ')')

				if (inner == 0)
					return i;

				else
					--inner;

			else if (c == '(')
				++inner;

		}

		return -1;

	}

}