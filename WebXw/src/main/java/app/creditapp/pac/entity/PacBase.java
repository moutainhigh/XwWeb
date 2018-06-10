package app.creditapp.pac.entity;

import app.base.BaseDomain;
import app.base.PUBParm;

/**
 * Title: PacBase.java Description:
 * 
 * @version：1.0
 **/
public class PacBase extends BaseDomain {
    private String tx_time;// 上传时间
    private String up_time;// 更新时间
    private String up_opno;// 更新人员
    private String pac_id;// 影像编号ID
    private String cif_no;// 客户号
    private String busi_no;// 业务编号
    private String pac_scene;// 影像场景
    private String pac_type_no;// 影像类型
    private String pac_name;// 影像名称
    private Double pac_size;// 影像大小
    private Integer down_cnt;// 下载次数
    private String pac_addr;// 影像存储地址
    private String enc_addr;// 影像加密地址
    private String rel_addr;// 影像相对地址
    private String pac_desc;// 影像描述
    private String field1;// 预留字段1
    private String field2;// 预留字段2
    private String filler;// 备注
    private String br_no;// 登记机构
    private String op_no;// 操作员

    

    /** 
     * @方法说明: 获取上传状态
     * @return void
     * @修改说明:
     */
    public String getUp_sts() {
	return pac_id != null && !"".equals(pac_id) ? PUBParm.DOC_UP_STS_02
		: PUBParm.DOC_UP_STS_01;
    }
    /**
     * @方法说明: 获取文件后缀
     * @return
     * @return  String
     * @修改说明:
     */
    public String getPac_suf() {
	return pac_addr.substring(pac_addr.lastIndexOf(".")+1).toLowerCase();
    }
    /**
     * @return the tx_time
     */
    public String getTx_time() {
        return tx_time;
    }
    /**
     * @param tx_time the tx_time to set
     */
    public void setTx_time(String tx_time) {
        this.tx_time = tx_time;
    }
    /**
     * @return the up_time
     */
    public String getUp_time() {
        return up_time;
    }
    /**
     * @param up_time the up_time to set
     */
    public void setUp_time(String up_time) {
        this.up_time = up_time;
    }
    /**
     * @return the up_opno
     */
    public String getUp_opno() {
        return up_opno;
    }
    /**
     * @param up_opno the up_opno to set
     */
    public void setUp_opno(String up_opno) {
        this.up_opno = up_opno;
    }
    /**
     * @return the pac_id
     */
    public String getPac_id() {
        return pac_id;
    }
    /**
     * @param pac_id the pac_id to set
     */
    public void setPac_id(String pac_id) {
        this.pac_id = pac_id;
    }
    /**
     * @return the cif_no
     */
    public String getCif_no() {
        return cif_no;
    }
    /**
     * @param cif_no the cif_no to set
     */
    public void setCif_no(String cif_no) {
        this.cif_no = cif_no;
    }
    /**
     * @return the busi_no
     */
    public String getBusi_no() {
        return busi_no;
    }
    /**
     * @param busi_no the busi_no to set
     */
    public void setBusi_no(String busi_no) {
        this.busi_no = busi_no;
    }
    /**
     * @return the pac_scene
     */
    public String getPac_scene() {
        return pac_scene;
    }
    /**
     * @param pac_scene the pac_scene to set
     */
    public void setPac_scene(String pac_scene) {
        this.pac_scene = pac_scene;
    }
    /**
     * @return the pac_typeNo
     */
    public String getPac_type_no() {
        return pac_type_no;
    }
    /**
     * @param pac_typeNo the pac_typeNo to set
     */
    public void setPac_type_no(String pac_type_no) {
        this.pac_type_no = pac_type_no;
    }
    /**
     * @return the pac_name
     */
    public String getPac_name() {
        return pac_name;
    }
    /**
     * @param pac_name the pac_name to set
     */
    public void setPac_name(String pac_name) {
        this.pac_name = pac_name;
    }
    /**
     * @return the pac_size
     */
    public Double getPac_size() {
        return pac_size;
    }
    /**
     * @param pac_size the pac_size to set
     */
    public void setPac_size(Double pac_size) {
        this.pac_size = pac_size;
    }
    /**
     * @return the down_cnt
     */
    public Integer getDown_cnt() {
        return down_cnt;
    }
    /**
     * @param down_cnt the down_cnt to set
     */
    public void setDown_cnt(Integer down_cnt) {
        this.down_cnt = down_cnt;
    }
    /**
     * @return the pac_addr
     */
    public String getPac_addr() {
        return pac_addr;
    }
    /**
     * @param pac_addr the pac_addr to set
     */
    public void setPac_addr(String pac_addr) {
        this.pac_addr = pac_addr;
    }
    /**
     * @return the enc_addr
     */
    public String getEnc_addr() {
        return enc_addr;
    }
    /**
     * @param enc_addr the enc_addr to set
     */
    public void setEnc_addr(String enc_addr) {
        this.enc_addr = enc_addr;
    }
    /**
     * @return the rel_addr
     */
    public String getRel_addr() {
        return rel_addr;
    }
    /**
     * @param rel_addr the rel_addr to set
     */
    public void setRel_addr(String rel_addr) {
        this.rel_addr = rel_addr;
    }
    /**
     * @return the pac_desc
     */
    public String getPac_desc() {
        return pac_desc;
    }
    /**
     * @param pac_desc the pac_desc to set
     */
    public void setPac_desc(String pac_desc) {
        this.pac_desc = pac_desc;
    }
    /**
     * @return the field1
     */
    public String getField1() {
        return field1;
    }
    /**
     * @param field1 the field1 to set
     */
    public void setField1(String field1) {
        this.field1 = field1;
    }
    /**
     * @return the field2
     */
    public String getField2() {
        return field2;
    }
    /**
     * @param field2 the field2 to set
     */
    public void setField2(String field2) {
        this.field2 = field2;
    }
    /**
     * @return the filler
     */
    public String getFiller() {
        return filler;
    }
    /**
     * @param filler the filler to set
     */
    public void setFiller(String filler) {
        this.filler = filler;
    }
    /**
     * @return the br_no
     */
    public String getBr_no() {
        return br_no;
    }
    /**
     * @param br_no the br_no to set
     */
    public void setBr_no(String br_no) {
        this.br_no = br_no;
    }
    /**
     * @return the op_no
     */
    public String getOp_no() {
        return op_no;
    }
    /**
     * @param op_no the op_no to set
     */
    public void setOp_no(String op_no) {
        this.op_no = op_no;
    }
}