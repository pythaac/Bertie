# DROP TABLE IF EXISTS member;
# DROP TABLE IF EXISTS post;

CREATE TABLE IF NOT EXISTS member
(
    id          VARCHAR(32),
    password    VARCHAR(32),

    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS post
(
    num         BIGINT AUTO_INCREMENT,
    title       VARCHAR(64),
    content     TEXT,
    id          VARCHAR(32),
    time        DATETIME,

    PRIMARY KEY (num)
)