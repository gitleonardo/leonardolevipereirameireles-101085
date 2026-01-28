package com.leonardo.musicapi.common.exception;

import java.time.Instant;
import java.util.List;

public class ApiErrorResponse {

    private final Instant timestamp;
    private final int status;
    private final String erro;
    private final String caminho;
    private final List<String> detalhes;
    private final List<CampoErro> campos;

    public ApiErrorResponse(Instant timestamp,
                            int status,
                            String erro,
                            String caminho,
                            List<String> detalhes,
                            List<CampoErro> campos) {
        this.timestamp = timestamp;
        this.status = status;
        this.erro = erro;
        this.caminho = caminho;
        this.detalhes = detalhes;
        this.campos = campos;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public int getStatus() {
        return status;
    }

    public String getErro() {
        return erro;
    }

    public String getCaminho() {
        return caminho;
    }

    public List<String> getDetalhes() {
        return detalhes;
    }

    public List<CampoErro> getCampos() {
        return campos;
    }

    public static class CampoErro {
        private final String campo;
        private final String mensagem;

        public CampoErro(String campo, String mensagem) {
            this.campo = campo;
            this.mensagem = mensagem;
        }

        public String getCampo() {
            return campo;
        }

        public String getMensagem() {
            return mensagem;
        }
    }
}
