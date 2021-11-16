package isep.financialai.domain;

public class Fact {
    static private Long lastId = 0L;
    private Long id;

    public Fact() {
        Fact.lastId ++;
        this.id = lastId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
