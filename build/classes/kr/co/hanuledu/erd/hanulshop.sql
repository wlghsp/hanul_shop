
/* Drop Tables */

DROP TABLE board CASCADE CONSTRAINTS;
DROP TABLE member CASCADE CONSTRAINTS;




/* Create Tables */

CREATE TABLE board
(
	articleNo number(10,0) NOT NULL,
	subject varchar2(50) NOT NULL,
	content varchar2(150) NOT NULL,
	passwd varchar2(60),
	reg_date timestamp NOT NULL,
	readcount number,
	ref number NOT NULL,
	re_step number NOT NULL,
	re_level number NOT NULL,
	filename varchar2(150),
	-- 회원 아이디 
	id varchar2(20) NOT NULL,
	PRIMARY KEY (articleNo)
);


CREATE TABLE member
(
	-- 회원 아이디 
	id varchar2(20) NOT NULL,
	-- 회원 패스워드
	pwd varchar2(20) NOT NULL,
	-- 회원 이름 
	name varchar2(40) NOT NULL,
	-- 회원 이메일
	email varchar2(40) NOT NULL,
	-- 회원 주소
	address varchar2(40) NOT NULL,
	-- 회원 전화번호 
	phone varchar2(20) NOT NULL,
	-- 회원탈퇴여부 
	useyn char(1) NOT NULL,
	-- 회원 가입일 
	indate date NOT NULL,
	-- 회원 우편번호 
	zip_num varchar2(20) NOT NULL,
	PRIMARY KEY (id)
);



/* Create Foreign Keys */

ALTER TABLE board
	ADD FOREIGN KEY (id)
	REFERENCES member (id)
;



/* Comments */

COMMENT ON COLUMN board.id IS '회원 아이디 ';
COMMENT ON COLUMN member.id IS '회원 아이디 ';
COMMENT ON COLUMN member.pwd IS '회원 패스워드';
COMMENT ON COLUMN member.name IS '회원 이름 ';
COMMENT ON COLUMN member.email IS '회원 이메일';
COMMENT ON COLUMN member.address IS '회원 주소';
COMMENT ON COLUMN member.phone IS '회원 전화번호 ';
COMMENT ON COLUMN member.useyn IS '회원탈퇴여부 ';
COMMENT ON COLUMN member.indate IS '회원 가입일 ';
COMMENT ON COLUMN member.zip_num IS '회원 우편번호 ';



