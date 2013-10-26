--数据库名称zhaoydb

--show tables;
--下面是建表语句
create table zynews(
	id bigint(20) primary key auto_increment,
	title varchar(100),
	content text,
	newsdesc varchar(200),
	createtime datetime,
	newskwd varchar(100)
);
create table rule(
	id bigint(20) primary key auto_increment,
	ruleid varchar(100),
	ruledef varchar(200),
	ruletype varchar(100)
);

insert into rule(ruleid,ruledef) values('IndexNewsSmallImg','image/index/2013-36_6116457.jpg');
--select * from zynews;

--update zynews set title='怎样报名参加淘宝一周年庆典',content='xxxxx',createtime='2012-2-2',newskwd='aaaa',newsdesc='afdsdsd';

--select * from rule where ruleid="IndexNewsList";
insert into rule(ruleid,ruledef) values("IndexNewsList","1,2,3");
insert into rule(ruleid,ruledef) values("IndexNewsDesc","美国海军作战部副部长格林纳特上将9月5日在华盛顿的讲话中首次提到，美国将在日本部署濒海战斗");
insert into rule(ruleid,ruledef) values("HotNewsBgImg","image/news/notic_bg.jpg");
insert into rule(ruleid,ruledef) values("HotNewsList","1,2,3");
insert into rule(ruleid,ruledef) values("SchoolDescContent","朝之阳");
insert into rule(ruleid,ruledef) values("EducationConditionContent","很棒");

insert into rule(ruleid,ruledef) values("SchoolAddressContent","矿院");
insert into rule(ruleid,ruledef) values("DownloadDesc","您同样可以通过Nibiru客户端查看以下游戏，并且应用中提供了游戏的推荐下载方式xxxxxxx");
insert into rule(ruleid,ruledef) values("DownloadNoticeList","矿院;马鞍山;朝阳");
insert into rule(ruleid,ruledef,ruletype) values("DownloadSrcType","1,2,3,4","语文,数学,英语,历史");
insert into rule(ruleid,ruledef) values("NoticePanelList","1,2,3");
update rule set ruledef='[{"content":"把被选元素逐渐改变至给定的不透明度","href":"http://www.baidu.com","id":1}]' where ruleid="DownloadNoticeList";

create table download(
	id bigint(20) primary key auto_increment,
	href varchar(100),
	srcname varchar(200),
	upltime datetime,
	srctype int(5)
);
create table notice(
	id bigint(20) primary key auto_increment,
	title varchar(200),
	createtime datetime,
	content text
);
insert into notice(title) values('脏');
insert into rule(ruleid,ruledef) values('RecruitmentInfoList','[{"id":1,"content","“Nibiru”他是众神中最伟大的天体，它的回归预示着新的时代的开始。而在触摸游戏大肆横行的当下神"}]');
select * from rule;
update rule set ruledef='[{"id":1,"content":"“Nibiru”他是众神中最伟大的天体，它的回归预示着新的时代的开始。而在触摸游戏大肆横行的当下神"}]' where ruleid='RecruitmentInfoList';

insert into rule(ruleid,ruledef) values('RecruitmentImg','image/recruitment/04.gif');
update rule set ruledef='image/recruitment/01.gif' where ruleid='RecruitmentImg';
insert into rule(ruleid,ruledef) values('TeachersList','[{"id":1,"teaName":"张鹏","service":"初中各层次水平学生","feature":"1965年7月出生的朱界飞当教师已有二十五个年头了，二十多年的教学生涯铸","project":"数学","photoImg":"image/teacher/zhangpeng.jpg"}]');
insert into rule(ruleid,ruledef) values('StudentsList','[{"id":1,"stuName":"王见","fromSchool":"xxxx","toSchool":"gggg","score":231,"desc":"dssss","imgPath":"image/student/stu02.gif"}]');


--学科表
create table subject (
	id bigint(20) primary key auto_increment,
	grade varchar(10),
	gradeCode int(2),
	subjectName varchar(10)
);

insert into subject(grade, gradecode, subjectname) values('一年级', 1, '语文');
insert into subject(grade, gradecode, subjectname) values('一年级', 1, '数学');
insert into subject(grade, gradecode, subjectname) values('二年级', 2, '语文');
insert into subject(grade, gradecode, subjectname) values('二年级', 2, '数学');
insert into subject(grade, gradecode, subjectname) values('三年级', 3, '语文');

select * from subject;
delete from subject where id=3;
commit;

--课程表
create table zyclass (
	id bigint(20) primary key auto_increment,
	classname varchar(20),
	imgurl varchar(50),
	teachername varchar(10),
	volumn int(1),
	subjectid bigint(20) references subject(id)
);

commit;


