CREATE TABLE IF NOT EXISTS document
(
    doc_number  BIGINT PRIMARY KEY NOT NULL UNIQUE,
    date        DATE,
    sum         DOUBLE PRECISION,
    description VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS specification
(
    id            BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY UNIQUE,
    name          VARCHAR(64)      NOT NULL,
    sum           DOUBLE PRECISION NOT NULL,
    doc_number_id BIGINT           NOT NULL,

    CONSTRAINT doc_number_fk FOREIGN KEY (doc_number_id) REFERENCES document (doc_number)
);

CREATE TABLE IF NOT EXISTS logs
(
    id        BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY UNIQUE,
    date_time TIMESTAMP    NOT NULL,
    url       VARCHAR(255) NOT NULL,
    status    BIGINT       NOT NULL,
    error     VARCHAR(255) NOT NULL,
    message   VARCHAR(255) NOT NULL
);