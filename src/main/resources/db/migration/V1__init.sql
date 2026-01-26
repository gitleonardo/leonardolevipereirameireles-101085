CREATE TABLE artista (
  id BIGSERIAL PRIMARY KEY,
  nome VARCHAR(200) NOT NULL,
  criado_em TIMESTAMP NOT NULL DEFAULT NOW(),
  atualizado_em TIMESTAMP NOT NULL DEFAULT NOW()
);

CREATE TABLE album (
  id BIGSERIAL PRIMARY KEY,
  titulo VARCHAR(200) NOT NULL,
  criado_em TIMESTAMP NOT NULL DEFAULT NOW(),
  atualizado_em TIMESTAMP NOT NULL DEFAULT NOW()
);

CREATE TABLE artista_album (
  artista_id BIGINT NOT NULL,
  album_id BIGINT NOT NULL,
  PRIMARY KEY (artista_id, album_id),
  CONSTRAINT fk_artista_album_artista
    FOREIGN KEY (artista_id) REFERENCES artista (id),
  CONSTRAINT fk_artista_album_album
    FOREIGN KEY (album_id) REFERENCES album (id)
);

CREATE INDEX idx_artista_nome ON artista (nome);
CREATE INDEX idx_album_titulo ON album (titulo);