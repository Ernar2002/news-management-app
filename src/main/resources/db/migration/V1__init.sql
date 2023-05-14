CREATE TABLE news
(
    id         UUID NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE,
    updated_at TIMESTAMP WITHOUT TIME ZONE,
    active     VARCHAR(255),
    title      VARCHAR(255),
    content    VARCHAR(255),
    source_id  UUID NOT NULL,
    topic_id   UUID NOT NULL,
    CONSTRAINT pk_news PRIMARY KEY (id)
);

CREATE TABLE sources
(
    id         UUID NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE,
    updated_at TIMESTAMP WITHOUT TIME ZONE,
    active     VARCHAR(255),
    name       VARCHAR(255),
    url        VARCHAR(255),
    CONSTRAINT pk_sources PRIMARY KEY (id)
);

CREATE TABLE topics
(
    id         UUID NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE,
    updated_at TIMESTAMP WITHOUT TIME ZONE,
    active     VARCHAR(255),
    name       VARCHAR(255),
    CONSTRAINT pk_topics PRIMARY KEY (id)
);

ALTER TABLE news
    ADD CONSTRAINT FK_NEWS_ON_SOURCE FOREIGN KEY (source_id) REFERENCES sources (id) ON DELETE CASCADE;

ALTER TABLE news
    ADD CONSTRAINT FK_NEWS_ON_TOPIC FOREIGN KEY (topic_id) REFERENCES topics (id) ON DELETE CASCADE;