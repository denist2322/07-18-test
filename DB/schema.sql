DROP DATABASE
IF EXISTS sbb;

CREATE DATABASE sbb;

CREATE TABLE Article (
    id INT(11) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `subject` VARCHAR(200) NOT NULL,
    content TEXT NOT NULL,
    create_date DATETIME NOT NULL,
    `view` INT UNSIGNED NOT NULL,
    reply_id INT(11) UNSIGNED
);

INSERT INTO Article SET
create_date = NOW(),
`subject` = '바니바니 바니바니',
content = '당근 당근',
`view`=0,
reply_id = 1;

INSERT INTO Article SET
create_date = NOW(),
`subject` = '딸기가 좋아 딸기가 좋아',
content = '좋아 좋아 좋아 좋아 좋아',
`view`=0,
reply_id = 2;

INSERT INTO Article SET
create_date = NOW(),
`subject` = '베스킨라빈스 31',
content = '귀엽고 깜찍하게 31',
`view`=0,
reply_id = 3;

CREATE TABLE Reply (
    id INT(11) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    content TEXT NOT NULL,
    create_date DATETIME NOT NULL,
    reply_like INT(2) UNSIGNED NOT NULL,
    article_id INT(11) UNSIGNED NOT NULL
);

INSERT INTO Reply SET
create_date = NOW(),
content = '재밌게 노시네요;;',
reply_like = 0,
article_id = 1;

INSERT INTO Reply SET
create_date = NOW(),
content = '저는 바나나가 좋아요',
reply_like = 0,
article_id = 2;

INSERT INTO Reply SET
create_date = NOW(),
content = '베라는 역시 엄마는 외계인이죠!',
reply_like = 1,
article_id = 3;

SELECT * FROM Article;
SELECT * FROM Reply;
