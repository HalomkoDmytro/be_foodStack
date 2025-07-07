CREATE SEQUENCE article_seq start WITH 1 INCREMENT by 50;
CREATE SEQUENCE list_group_element_seq start WITH 1 INCREMENT by 50;
CREATE SEQUENCE paragraph_seq start WITH 1 INCREMENT by 50;
CREATE SEQUENCE users_seq START WITH 1 INCREMENT BY 1;

create table article (
    created_at timestamp(6),
    id bigint not null,
    updated_at timestamp(6),
    src_img varchar(1000),
    title varchar(1000),
    description clob,
    theme enum ('DESSERT','MAIN_COURSE','SOUS'),
    primary key (id)
);

create table article_paragraph (
    article_id bigint not null,
    paragraph_id bigint not null unique
);

create table list_group_element (
    id bigint not null,
    user_id bigint,
    size varchar(255),
    text varchar(255),
    primary key (id)
);

create table list_groups (
    id bigint not null,
    primary key (id)
);

create table paragraph (
    order_position integer not null,
    article_id bigint not null,
    id bigint not null,
    type enum ('LIST_GROUPS','PICTURE','PICTURE_WITH_TEXT','TEXT'),
    primary key (id)
);

create table picture (
    id bigint not null,
    data clob,
    primary key (id)
);

create table text (
    id bigint not null,
    data clob,
    primary key (id)
);

create table user_roles (
    user_id bigint not null,
    role enum ('ADMIN','USER')
);

create table users (
    created_at timestamp(6),
    id bigint not null,
    username varchar(50) not null unique,
    password varchar(255) not null,
    primary key (id)
);

alter table if exists article_paragraph add constraint FK9ik79p6j9k50y2ol043avnsb3 foreign key (paragraph_id) references paragraph;
alter table if exists article_paragraph add constraint FKogyuhxpasrqwb8t4msrp369l9 foreign key (article_id) references article;
alter table if exists list_group_element add constraint FKipnb1ipxrw4dx9en6h9krfvfn foreign key (user_id) references list_groups;
alter table if exists list_groups add constraint FKpmbmirr8qpdwv8vgoy4nvcxbg foreign key (id) references paragraph;
alter table if exists paragraph add constraint FKpkqkcgary69yaal0ylw0u7w6h foreign key (article_id) references article;
alter table if exists picture add constraint FK1im42mpjs60t4u324xv1rcpsn foreign key (id) references paragraph;
alter table if exists text add constraint FKkouupa3dxvbt3kr0bgudkhipe foreign key (id) references paragraph;
alter table if exists user_roles add constraint FKhfh9dx7w3ubf1co1vdev94g3f foreign key (user_id) references users;
