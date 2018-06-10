
package app.creditapp.inf.client.zf;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ws.client.zf package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _DoAction_QNAME = new QName("http://ws.inf.creditapp.app/", "doAction");
   // private final static QName _Exception_QNAME = new QName("http://ws.inf.creditapp.app/", "Exception");
    private final static QName _DoActionResponse_QNAME = new QName("http://ws.inf.creditapp.app/", "doActionResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ws.client.zf
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DoAction }
     * 
     */
    public DoAction createDoAction() {
        return new DoAction();
    }

    /**
     * Create an instance of {@link Exception }
     * 
     */
    public Exception createException() {
        return new Exception();
    }

    /**
     * Create an instance of {@link DoActionResponse }
     * 
     */
    public DoActionResponse createDoActionResponse() {
        return new DoActionResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DoAction }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.inf.creditapp.app/", name = "doAction")
    public JAXBElement<DoAction> createDoAction(DoAction value) {
        return new JAXBElement<DoAction>(_DoAction_QNAME, DoAction.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Exception }{@code >}}
     * 
     */
   

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DoActionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.inf.creditapp.app/", name = "doActionResponse")
    public JAXBElement<DoActionResponse> createDoActionResponse(DoActionResponse value) {
        return new JAXBElement<DoActionResponse>(_DoActionResponse_QNAME, DoActionResponse.class, null, value);
    }

}
