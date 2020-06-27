package pl.piomin.microservices.document.model;

public class Document {

    private Integer id;
    private Integer customerId;
    private String content;

    public Document() {

    }

    public Document(Integer id, Integer customerId, String content) {
        this.id = id;
        this.customerId = customerId;
        this.content = content;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
