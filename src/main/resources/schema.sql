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
);

CREATE TABLE IF NOT EXISTS naver_api_info
(
    client_id       VARCHAR(128),
    client_secret   VARCHAR(128),

    PRIMARY KEY (client_id)
);

CREATE TABLE IF NOT EXISTS kakao_api_info
(
    rest_api_key       VARCHAR(128),

    PRIMARY KEY (rest_api_key)
);