package cl.corona.bbookseason.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="bbook_temporada")
public class BbookEnviaSeason {

    @Id
    @Column(name="idd" ,columnDefinition = "varchar2(100)")
    private String id;

    @Column(name="atr_code" ,columnDefinition = "varchar2(10)")
    private String atrCode;

    @Column(name="atr_code_desc" ,columnDefinition = "varchar2(50)")
    protected String atrCodeDesc;

    @Column(name="inactive" ,columnDefinition = "varchar2(10)")
    protected String inactive;

    @Column(name="tran_type" ,columnDefinition = "varchar2(1)")
    protected String tranType;

    public BbookEnviaSeason() {
        super();
    }

    public BbookEnviaSeason(String id, String atrCode, String atrCodeDesc, String inactive, String tranType) {
        super();
        this.id = id;
        this.atrCode = atrCode;
        this.atrCodeDesc = atrCodeDesc;
        this.inactive = inactive;
        this.tranType = tranType;

    }

    @Override
    public String toString() {
        return "BbookEnviaSeason [id=" + id + ", atrCode=" + atrCode
                + ", atrCodeDesc=" + atrCodeDesc + ", inactive=" + inactive + ", tranType=" + tranType +"]";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAtrCode() {
        return atrCode;
    }

    public void setAtrCode(String atrCode) {
        this.atrCode = atrCode;
    }

    public String getAtrCodeDesc() {
        return atrCodeDesc;
    }

    public void setAtrCodeDesc(String atrCodeDesc) {
        this.atrCodeDesc = atrCodeDesc;
    }

    public String getInactive() {
        return inactive;
    }

    public void setInactive(String inactive) {
        this.inactive = inactive;
    }

    public String getTranType() {
        return tranType;
    }

    public void setTranType(String tranType) {
        this.tranType = tranType;
    }

}
