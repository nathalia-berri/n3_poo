package transacao;

public class Transacao {
    private String codigo;
    private String descricao;
    private double valor;
    private String tipo; // "CREDITO" ou "DEBITO"

    public Transacao(String descricao, double valor, String tipo) {
        this.descricao = descricao;
        this.valor = valor;
        this.tipo = tipo;
        gerarCodigo();
    }

    private void gerarCodigo() {
        if (tipo.equalsIgnoreCase("CREDITO")) {
            this.codigo = "CRE" + System.currentTimeMillis();
        } else if (tipo.equalsIgnoreCase("DEBITO")) {
            this.codigo = "DEB" + System.currentTimeMillis();
        }
    }

    public String getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
