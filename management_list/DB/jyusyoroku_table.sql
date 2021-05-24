CREATE TABLE kensyudb.category
(
    categoryid                  VARCHAR(1) NOT NULL,
    categoryname                VARCHAR(10) NOT NULL,
);

CREATE TABLE kensyudb.jyusyoroku
(
    id                          INT NOT NULL,
    name                        VARCHAR(40) NOT NULL,
    address                     VARCHAR(80) NOT NULL,
    tel                         VARCHAR(11),
    categoryid                  VARCHAR(1),
    delete_flg                  VARCHAR(1),
    CONSTRAINT PRIMARY KEY (id)
);