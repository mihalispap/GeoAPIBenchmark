package com.doubleip.geoapibenchmark.model.postgres;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="compfoo")
public class CompFoo {

    @Id
    private Long id;

    private Long f1;

    private Long f2;

    public CompFoo(Long f1, Long f2) {
        this.f1 = f1;
        this.f2 = f2;
    }

    public CompFoo() {
    }

    public Long getF1() {
        return f1;
    }

    public void setF1(Long f1) {
        this.f1 = f1;
    }

    public Long getF2() {
        return f2;
    }

    public void setF2(Long f2) {
        this.f2 = f2;
    }
}
