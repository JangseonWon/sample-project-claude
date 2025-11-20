package com.gcgenome.lims.test;

public class Exon {
    public final String gene;
    public final String exon;
    public final String reference;

    public Exon(String gene, String exon, String reference) {
        this.gene = gene;
        this.exon = exon;
        this.reference = reference;
    }

    public String gene() {
        return this.gene;
    }

    public String exon() {
        return this.exon;
    }

    public String reference() {
        return this.reference;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Exon)) return false;
        final Exon other = (Exon) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$gene = this.gene();
        final Object other$gene = other.gene();
        if (this$gene == null ? other$gene != null : !this$gene.equals(other$gene)) return false;
        final Object this$exon = this.exon();
        final Object other$exon = other.exon();
        if (this$exon == null ? other$exon != null : !this$exon.equals(other$exon)) return false;
        final Object this$reference = this.reference();
        final Object other$reference = other.reference();
        if (this$reference == null ? other$reference != null : !this$reference.equals(other$reference)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Exon;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $gene = this.gene();
        result = result * PRIME + ($gene == null ? 43 : $gene.hashCode());
        final Object $exon = this.exon();
        result = result * PRIME + ($exon == null ? 43 : $exon.hashCode());
        final Object $reference = this.reference();
        result = result * PRIME + ($reference == null ? 43 : $reference.hashCode());
        return result;
    }

    public String toString() {
        return "Exon(gene=" + this.gene() + ", exon=" + this.exon() + ", reference=" + this.reference() + ")";
    }
}
