--
-- PostgreSQL database dump
--

-- Dumped from database version 13.6 (Ubuntu 13.6-1.pgdg20.04+1)
-- Dumped by pg_dump version 13.6 (Ubuntu 13.6-1.pgdg20.04+1+b1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: days_sprints; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.days_sprints (
    sprint_id bigint NOT NULL,
    days_array character varying(1000)
);


ALTER TABLE public.days_sprints OWNER TO postgres;

--
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.hibernate_sequence OWNER TO postgres;

--
-- Name: ideall_sprints; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.ideall_sprints (
    sprint_id bigint NOT NULL,
    il_array character varying(1000)
);


ALTER TABLE public.ideall_sprints OWNER TO postgres;

--
-- Name: jcoverage; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.jcoverage (
    id bigint NOT NULL,
    covered real NOT NULL,
    created_at timestamp without time zone,
    missed real NOT NULL,
    percentage real NOT NULL,
    projectname character varying(255),
    totalpercentage real NOT NULL,
    type character varying(255)
);


ALTER TABLE public.jcoverage OWNER TO postgres;

--
-- Name: jcoverage_generator; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.jcoverage_generator
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.jcoverage_generator OWNER TO postgres;

--
-- Name: project_generator; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.project_generator
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.project_generator OWNER TO postgres;

--
-- Name: projet_more_sp; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.projet_more_sp (
    projet_id bigint NOT NULL,
    project_more_sp character varying(1000)
);


ALTER TABLE public.projet_more_sp OWNER TO postgres;

--
-- Name: projet_percentage_spc; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.projet_percentage_spc (
    projet_id bigint NOT NULL,
    percentage_spc character varying(1000)
);


ALTER TABLE public.projet_percentage_spc OWNER TO postgres;

--
-- Name: projet_sp_commitment; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.projet_sp_commitment (
    projet_id bigint NOT NULL,
    project_sp_commitment character varying(1000)
);


ALTER TABLE public.projet_sp_commitment OWNER TO postgres;

--
-- Name: projet_sp_completed; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.projet_sp_completed (
    projet_id bigint NOT NULL,
    project_sp_completed character varying(1000)
);


ALTER TABLE public.projet_sp_completed OWNER TO postgres;

--
-- Name: projets; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.projets (
    id bigint NOT NULL,
    date_debut date,
    date_fin date,
    description text,
    p_reference character varying(15),
    pupdated_date timestamp without time zone,
    title text,
    totalsp_commitment integer DEFAULT 0,
    totalsp_completed integer DEFAULT 0,
    totalstorypointsinitiallycounts integer DEFAULT 0
);


ALTER TABLE public.projets OWNER TO postgres;

--
-- Name: roles; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.roles (
    id integer NOT NULL,
    name character varying(20)
);


ALTER TABLE public.roles OWNER TO postgres;

--
-- Name: sprint_generator; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.sprint_generator
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.sprint_generator OWNER TO postgres;

--
-- Name: sprints; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.sprints (
    id bigint NOT NULL,
    more_sp integer DEFAULT 0,
    s_reference character varying(15),
    sdate_debut date,
    sdate_fin date,
    description text,
    title text,
    supdated_date timestamp without time zone,
    work_commitment integer DEFAULT 0,
    work_completed integer DEFAULT 0,
    projet_id bigint NOT NULL
);


ALTER TABLE public.sprints OWNER TO postgres;

--
-- Name: story; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.story (
    id bigint NOT NULL,
    plus_sp integer DEFAULT 0,
    priority bigint,
    sp_completed integer DEFAULT 0,
    st_reference character varying(15),
    description text,
    title text,
    story_point integer DEFAULT 0,
    stupdated_date timestamp without time zone,
    sprint_id bigint NOT NULL
);


ALTER TABLE public.story OWNER TO postgres;

--
-- Name: story_generator; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.story_generator
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.story_generator OWNER TO postgres;

--
-- Name: task_generator; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.task_generator
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.task_generator OWNER TO postgres;

--
-- Name: tasks; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tasks (
    id bigint NOT NULL,
    bugs integer DEFAULT 0,
    estimation integer DEFAULT 0,
    status character varying(255),
    t_reference character varying(15),
    tdate_debut date,
    tdate_fin date,
    description text,
    title text,
    tsupdated_date timestamp without time zone,
    type_task character varying(255),
    story_id bigint NOT NULL
);


ALTER TABLE public.tasks OWNER TO postgres;

--
-- Name: user_generator; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.user_generator
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.user_generator OWNER TO postgres;

--
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    id bigint NOT NULL,
    email character varying(255),
    password character varying(255),
    username character varying(255)
);


ALTER TABLE public.users OWNER TO postgres;

--
-- Name: users_roles; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users_roles (
    user_id bigint NOT NULL,
    role_id integer NOT NULL
);


ALTER TABLE public.users_roles OWNER TO postgres;

--
-- Name: workedl_sprints; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.workedl_sprints (
    sprint_id bigint NOT NULL,
    workedl_array character varying(1000)
);


ALTER TABLE public.workedl_sprints OWNER TO postgres;

--
-- Data for Name: days_sprints; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.days_sprints (sprint_id, days_array) FROM stdin;
2	01-07-2022
2	02-07-2022
2	03-07-2022
2	04-07-2022
2	05-07-2022
2	06-07-2022
2	07-07-2022
2	08-07-2022
2	09-07-2022
2	10-07-2022
2	11-07-2022
2	12-07-2022
2	13-07-2022
2	14-07-2022
2	15-07-2022
2	16-07-2022
2	17-07-2022
2	18-07-2022
2	19-07-2022
2	20-07-2022
2	21-07-2022
2	22-07-2022
2	23-07-2022
2	24-07-2022
2	25-07-2022
2	26-07-2022
2	27-07-2022
2	28-07-2022
2	29-07-2022
2	30-07-2022
2	31-07-2022
2	{"(01-07-2022,02-07-2022,03-07-2022,04-07-2022,05-07-2022,06-07-2022,07-07-2022,08-07-2022,09-07-2022,10-07-2022,11-07-2022,12-07-2022,13-07-2022,14-07-2022,15-07-2022,16-07-2022,17-07-2022,18-07-2022,19-07-2022,20-07-2022,21-07-2022,22-07-2022,23-07-2022,24-07-2022,25-07-2022,26-07-2022,27-07-2022,28-07-2022,29-07-2022,30-07-2022,31-07-2022)"}
1	01-06-2022
1	02-06-2022
1	03-06-2022
1	04-06-2022
1	05-06-2022
1	06-06-2022
1	07-06-2022
1	08-06-2022
1	09-06-2022
1	10-06-2022
1	11-06-2022
1	12-06-2022
1	13-06-2022
1	14-06-2022
1	15-06-2022
1	16-06-2022
1	17-06-2022
1	18-06-2022
1	19-06-2022
1	20-06-2022
1	21-06-2022
1	22-06-2022
1	23-06-2022
1	24-06-2022
1	25-06-2022
1	26-06-2022
1	27-06-2022
1	28-06-2022
1	29-06-2022
1	30-06-2022
1	{"(01-06-2022,02-06-2022,03-06-2022,04-06-2022,05-06-2022,06-06-2022,07-06-2022,08-06-2022,09-06-2022,10-06-2022,11-06-2022,12-06-2022,13-06-2022,14-06-2022,15-06-2022,16-06-2022,17-06-2022,18-06-2022,19-06-2022,20-06-2022,21-06-2022,22-06-2022,23-06-2022,24-06-2022,25-06-2022,26-06-2022,27-06-2022,28-06-2022,29-06-2022,30-06-2022)"}
3	01-08-2022
3	02-08-2022
3	03-08-2022
3	04-08-2022
3	05-08-2022
3	06-08-2022
3	07-08-2022
3	08-08-2022
3	09-08-2022
3	10-08-2022
3	11-08-2022
3	12-08-2022
3	13-08-2022
3	14-08-2022
3	15-08-2022
3	16-08-2022
3	17-08-2022
3	18-08-2022
3	19-08-2022
3	20-08-2022
3	21-08-2022
3	22-08-2022
3	23-08-2022
3	24-08-2022
3	25-08-2022
3	26-08-2022
3	27-08-2022
3	28-08-2022
3	29-08-2022
3	30-08-2022
3	31-08-2022
3	{"(01-08-2022,02-08-2022,03-08-2022,04-08-2022,05-08-2022,06-08-2022,07-08-2022,08-08-2022,09-08-2022,10-08-2022,11-08-2022,12-08-2022,13-08-2022,14-08-2022,15-08-2022,16-08-2022,17-08-2022,18-08-2022,19-08-2022,20-08-2022,21-08-2022,22-08-2022,23-08-2022,24-08-2022,25-08-2022,26-08-2022,27-08-2022,28-08-2022,29-08-2022,30-08-2022,31-08-2022)"}
4	01-09-2022
4	02-09-2022
4	03-09-2022
4	04-09-2022
4	05-09-2022
4	06-09-2022
4	07-09-2022
4	08-09-2022
4	09-09-2022
4	10-09-2022
4	11-09-2022
4	12-09-2022
4	13-09-2022
4	14-09-2022
4	15-09-2022
4	16-09-2022
4	17-09-2022
4	18-09-2022
4	19-09-2022
4	20-09-2022
4	21-09-2022
4	22-09-2022
4	23-09-2022
4	24-09-2022
4	25-09-2022
4	26-09-2022
4	27-09-2022
4	28-09-2022
4	29-09-2022
4	30-09-2022
4	{"(01-09-2022,02-09-2022,03-09-2022,04-09-2022,05-09-2022,06-09-2022,07-09-2022,08-09-2022,09-09-2022,10-09-2022,11-09-2022,12-09-2022,13-09-2022,14-09-2022,15-09-2022,16-09-2022,17-09-2022,18-09-2022,19-09-2022,20-09-2022,21-09-2022,22-09-2022,23-09-2022,24-09-2022,25-09-2022,26-09-2022,27-09-2022,28-09-2022,29-09-2022,30-09-2022)"}
5	01-10-2022
5	02-10-2022
5	03-10-2022
5	04-10-2022
5	05-10-2022
5	06-10-2022
5	07-10-2022
5	08-10-2022
5	09-10-2022
5	10-10-2022
5	11-10-2022
5	12-10-2022
5	13-10-2022
5	14-10-2022
5	15-10-2022
5	16-10-2022
5	17-10-2022
5	18-10-2022
5	19-10-2022
5	20-10-2022
5	21-10-2022
5	22-10-2022
5	23-10-2022
5	24-10-2022
5	25-10-2022
5	26-10-2022
5	27-10-2022
5	28-10-2022
5	29-10-2022
5	30-10-2022
5	31-10-2022
5	{"(01-10-2022,02-10-2022,03-10-2022,04-10-2022,05-10-2022,06-10-2022,07-10-2022,08-10-2022,09-10-2022,10-10-2022,11-10-2022,12-10-2022,13-10-2022,14-10-2022,15-10-2022,16-10-2022,17-10-2022,18-10-2022,19-10-2022,20-10-2022,21-10-2022,22-10-2022,23-10-2022,24-10-2022,25-10-2022,26-10-2022,27-10-2022,28-10-2022,29-10-2022,30-10-2022,31-10-2022)"}
\.


--
-- Data for Name: ideall_sprints; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.ideall_sprints (sprint_id, il_array) FROM stdin;
2	6.0
2	5.8
2	5.6000004
2	5.4000006
2	5.200001
2	5.000001
2	4.800001
2	4.6000013
2	4.4000015
2	4.2000017
2	4.000002
2	3.8000019
2	3.6000018
2	3.4000018
2	3.2000017
2	3.0000017
2	2.8000016
2	2.6000016
2	2.4000015
2	2.2000015
2	2.0000014
2	1.8000014
2	1.6000013
2	1.4000013
2	1.2000012
2	1.0000012
2	0.8000012
2	0.6000012
2	0.40000123
2	0.20000122
2	{"(6.0,5.8,5.6000004,5.4000006,5.200001,5.000001,4.800001,4.6000013,4.4000015,4.2000017,4.000002,3.8000019,3.6000018,3.4000018,3.2000017,3.0000017,2.8000016,2.6000016,2.4000015,2.2000015,2.0000014,1.8000014,1.6000013,1.4000013,1.2000012,1.0000012,0.8000012,0.6000012,0.40000123,0.20000122)"}
1	23.0
1	22.206896
1	21.413792
1	20.620687
1	19.827583
1	19.03448
1	18.241375
1	17.44827
1	16.655167
1	15.862063
1	15.06896
1	14.275857
1	13.482754
1	12.689651
1	11.896547
1	11.103444
1	10.310341
1	9.517238
1	8.724134
1	7.931031
1	7.137928
1	6.344825
1	5.5517216
1	4.7586184
1	3.965515
1	3.1724114
1	2.379308
1	1.5862045
1	0.7931011
1	{"(23.0,22.206896,21.413792,20.620687,19.827583,19.03448,18.241375,17.44827,16.655167,15.862063,15.06896,14.275857,13.482754,12.689651,11.896547,11.103444,10.310341,9.517238,8.724134,7.931031,7.137928,6.344825,5.5517216,4.7586184,3.965515,3.1724114,2.379308,1.5862045,0.7931011)"}
3	3.0
3	2.9
3	2.8000002
3	2.7000003
3	2.6000004
3	2.5000005
3	2.4000006
3	2.3000007
3	2.2000008
3	2.1000009
3	2.000001
3	1.9000009
3	1.8000009
3	1.7000009
3	1.6000009
3	1.5000008
3	1.4000008
3	1.3000008
3	1.2000008
3	1.1000007
3	1.0000007
3	0.9000007
3	0.80000067
3	0.70000064
3	0.6000006
3	0.5000006
3	0.4000006
3	0.3000006
3	0.20000061
3	0.10000061
3	{"(3.0,2.9,2.8000002,2.7000003,2.6000004,2.5000005,2.4000006,2.3000007,2.2000008,2.1000009,2.000001,1.9000009,1.8000009,1.7000009,1.6000009,1.5000008,1.4000008,1.3000008,1.2000008,1.1000007,1.0000007,0.9000007,0.80000067,0.70000064,0.6000006,0.5000006,0.4000006,0.3000006,0.20000061,0.10000061)"}
4	5.0
4	4.827586
4	4.6551723
4	4.4827585
4	4.3103447
4	4.137931
4	3.965517
4	3.7931032
4	3.6206894
4	3.4482756
4	3.2758617
4	3.103448
4	2.931034
4	2.7586203
4	2.5862064
4	2.4137926
4	2.2413788
4	2.068965
4	1.8965511
4	1.7241373
4	1.5517235
4	1.3793097
4	1.2068958
4	1.034482
4	0.8620682
4	0.68965435
4	0.5172405
4	0.34482673
4	0.17241293
4	{"(5.0,4.827586,4.6551723,4.4827585,4.3103447,4.137931,3.965517,3.7931032,3.6206894,3.4482756,3.2758617,3.103448,2.931034,2.7586203,2.5862064,2.4137926,2.2413788,2.068965,1.8965511,1.7241373,1.5517235,1.3793097,1.2068958,1.034482,0.8620682,0.68965435,0.5172405,0.34482673,0.17241293)"}
5	3.0
5	2.9
5	2.8000002
5	2.7000003
5	2.6000004
5	2.5000005
5	2.4000006
5	2.3000007
5	2.2000008
5	2.1000009
5	2.000001
5	1.9000009
5	1.8000009
5	1.7000009
5	1.6000009
5	1.5000008
5	1.4000008
5	1.3000008
5	1.2000008
5	1.1000007
5	1.0000007
5	0.9000007
5	0.80000067
5	0.70000064
5	0.6000006
5	0.5000006
5	0.4000006
5	0.3000006
5	0.20000061
5	0.10000061
5	{"(3.0,2.9,2.8000002,2.7000003,2.6000004,2.5000005,2.4000006,2.3000007,2.2000008,2.1000009,2.000001,1.9000009,1.8000009,1.7000009,1.6000009,1.5000008,1.4000008,1.3000008,1.2000008,1.1000007,1.0000007,0.9000007,0.80000067,0.70000064,0.6000006,0.5000006,0.4000006,0.3000006,0.20000061,0.10000061)"}
\.


--
-- Data for Name: jcoverage; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.jcoverage (id, covered, created_at, missed, percentage, projectname, totalpercentage, type) FROM stdin;
\.


--
-- Data for Name: projet_more_sp; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.projet_more_sp (projet_id, project_more_sp) FROM stdin;
1	0
1	0
1	0
1	0
1	0
1	{"(0,0,0,0,0)"}
\.


--
-- Data for Name: projet_percentage_spc; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.projet_percentage_spc (projet_id, percentage_spc) FROM stdin;
1	0.00
1	19.38
1	16.88
1	14.38
1	16.25
1	{"(0.00,19.38,16.88,14.38,16.25)"}
\.


--
-- Data for Name: projet_sp_commitment; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.projet_sp_commitment (projet_id, project_sp_commitment) FROM stdin;
1	160
1	160
1	129
1	102
1	79
1	53
1	{"(160,160,129,102,79,53)"}
\.


--
-- Data for Name: projet_sp_completed; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.projet_sp_completed (projet_id, project_sp_completed) FROM stdin;
1	0
1	31
1	27
1	23
1	26
1	{"(0,31,27,23,26)"}
\.


--
-- Data for Name: projets; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.projets (id, date_debut, date_fin, description, p_reference, pupdated_date, title, totalsp_commitment, totalsp_completed, totalstorypointsinitiallycounts) FROM stdin;
1	2022-06-01	2022-10-31	description project 1	PUID1CA4E	2022-06-20 09:42:00.589891	project 1	40	107	160
\.


--
-- Data for Name: roles; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.roles (id, name) FROM stdin;
1	ROLE_PRODUCTOWNER
2	ROLE_SCRUMMASTER
3	ROLE_DEVELOPER
\.


--
-- Data for Name: sprints; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.sprints (id, more_sp, s_reference, sdate_debut, sdate_fin, description, title, supdated_date, work_commitment, work_completed, projet_id) FROM stdin;
2	0	SUID1CD4C	2022-07-01	2022-07-31	sprint 2 1	sprint 2 1	2022-06-20 09:42:00.55698	6	31	1
1	0	SUID12E67	2022-06-01	2022-06-30	sprint 1 1	sprint 1 1	2022-06-20 09:42:00.55698	23	0	1
3	0	SUID1CE23	2022-08-01	2022-08-31	sprint 3 1	sprint 3 1	2022-06-20 09:42:00.55698	3	27	1
4	0	SUID1BE93	2022-09-01	2022-09-30	sprint 4 1	sprint 4 1	2022-06-20 09:42:00.55698	5	23	1
5	0	SUID154C9	2022-10-01	2022-10-31	sprint 5 1	sprint 5 1	2022-06-20 09:42:00.55698	3	26	1
\.


--
-- Data for Name: story; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.story (id, plus_sp, priority, sp_completed, st_reference, description, title, story_point, stupdated_date, sprint_id) FROM stdin;
2	0	2	0	STUID1A4A4	story 2 1 1	story 2 1 1	9	2022-06-20 09:42:00.56492	1
4	0	2	15	STUID173B2	story 2 2 1	story 2 2 1	3	2022-06-20 09:42:00.56492	2
8	0	2	10	STUID12F5A	story 2 4 1	story 2 4 1	2	2022-06-20 09:42:00.56492	4
5	0	1	7	STUID1FFB7	story 1 3 1	story 1 3 1	3	2022-06-20 09:42:00.56492	3
7	0	1	13	STUID19DFD	story 1 4 1	story 1 4 1	3	2022-06-20 09:42:00.56492	4
3	0	1	16	STUID120C5	story 1 2 1	story 1 2 1	3	2022-06-20 09:42:00.56492	2
10	0	2	19	STUID1ED7D	story 2 5 1	story 2 5 1	0	2022-06-20 09:42:00.56492	5
9	0	1	7	STUID199C9	story 1 5 1	story 1 5 1	3	2022-06-20 09:42:00.56492	5
6	0	2	20	STUID1CE17	story 2 3 1	story 2 3 1	0	2022-06-20 09:42:00.56492	3
1	0	1	0	STUID11411	story 1 1 1	story 1 1 1	14	2022-06-20 09:42:00.56492	1
\.


--
-- Data for Name: tasks; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tasks (id, bugs, estimation, status, t_reference, tdate_debut, tdate_fin, description, title, tsupdated_date, type_task, story_id) FROM stdin;
63	0	3	Completed	TUID1EFF4	2022-09-04	2022-09-06	task 2 1 4 1	task 2 1 4 1	2022-06-20 09:42:00.581481	Default_task	7
156	0	3	Completed	TUID19217	2022-10-16	2022-10-18	task 1 2 5 1	task 1 2 5 1	2022-06-20 09:42:00.581481	Default_task	10
160	0	5	Completed	TUID1D69F	2022-10-29	2022-10-31	task 5 2 5 1	task 5 2 5 1	2022-06-20 09:42:00.581481	Default_task	10
153	0	2	Completed	TUID1A933	2022-10-08	2022-10-10	task 3 1 5 1	task 3 1 5 1	2022-06-20 09:42:00.581481	Default_task	9
159	0	3	Completed	TUID1FEC9	2022-10-26	2022-10-28	task 4 2 5 1	task 4 2 5 1	2022-06-20 09:42:00.581481	Default_task	10
8	0	2	Scheduled	TUID14A72	2022-06-26	2022-06-27	task 3 2 1 1	task 3 2 1 1	2022-06-20 09:42:00.581481	Default_task	2
10	0	1	Scheduled	TUID15B9F	2022-06-30	2022-06-30	task 5 2 1 1	task 5 2 1 1	2022-06-20 09:42:00.581481	Default_task	2
202	0	10	In_progress	TUID11DF9	2022-06-17	2022-06-20	task more 1 1 1	task more 1 1 1	2022-06-20 09:42:00.581481	Default_task	1
56	0	2	Completed	TUID12730	2022-08-13	2022-08-15	task 5 1 3 1	task 5 1 3 1	2022-06-20 09:42:00.581481	Default_task	5
154	0	3	Completed	TUID16035	2022-10-11	2022-10-13	task 4 1 5 1	task 4 1 5 1	2022-06-20 09:42:00.581481	Default_task	9
14	0	3	Completed	TUID1FC46	2022-07-11	2022-07-13	task 4 1 2 1	task 4 1 2 1	2022-06-20 09:42:00.581481	Default_task	3
16	0	3	Scheduled	TUID15C07	2022-07-18	2022-07-20	task 1 2 2 1	task 1 2 2 1	2022-06-20 09:42:00.581481	Default_task	4
70	0	2	Completed	TUID116D3	2022-09-26	2022-09-28	task 4 2 4 1	task 4 2 4 1	2022-06-20 09:42:00.581481	Default_task	8
158	0	5	Completed	TUID16AF8	2022-10-22	2022-10-25	task 3 2 5 1	task 3 2 5 1	2022-06-20 09:42:00.581481	Default_task	10
157	0	3	Completed	TUID1AEE1	2022-10-19	2022-10-21	task 2 2 5 1	task 2 2 5 1	2022-06-20 09:42:00.581481	Default_task	10
152	0	3	Scheduled	TUID11C7F	2022-10-05	2022-10-07	task 2 1 5 1	task 2 1 5 1	2022-06-20 09:42:00.581481	Default_task	9
155	0	2	Completed	TUID166D0	2022-10-14	2022-10-15	task 5 1 5 1	task 5 1 5 1	2022-06-20 09:42:00.581481	Default_task	9
60	0	3	Completed	TUID131EC	2022-08-27	2022-08-29	task 4 2 3 1	task 4 2 3 1	2022-06-20 09:42:00.581481	Default_task	6
13	0	5	Completed	TUID162DB	2022-07-06	2022-07-10	task 3 1 2 1	task 3 1 2 1	2022-06-20 09:42:00.581481	Default_task	3
67	0	2	Scheduled	TUID1A853	2022-09-18	2022-09-19	task 1 2 4 1	task 1 2 4 1	2022-06-20 09:42:00.581481	Default_task	8
7	0	2	Scheduled	TUID172B7	2022-06-24	2022-06-25	task 2 2 1 1	task 2  2 1 1	2022-06-20 09:42:00.581481	Default_task	2
1	0	2	Scheduled	TUID10CF6	2022-06-01	2022-06-04	task  1 1 1 1	task  1 1 1 1	2022-06-20 09:42:00.581481	Default_task	1
54	2	3	Scheduled	TUID16732	2022-08-07	2022-08-09	task 3 1 3 1	task 3 1 3 1	2022-06-20 09:42:00.581481	Default_task	5
17	1	2	Completed	TUID10F5E	2022-07-21	2022-07-22	task 2 2 2 1	task 2 2 2 1	2022-06-20 09:42:00.581481	Default_task	4
3	0	5	Scheduled	TUID112A3	2022-06-09	2022-06-12	task  3 1 1 1	task  3 1 1 1	2022-06-20 09:42:00.581481	Default_task	1
69	0	2	Completed	TUID1F582	2022-09-24	2022-09-25	task 3 2 4 1	task 3 2 4 1	2022-06-20 09:42:00.581481	Default_task	8
20	0	2	Completed	TUID15FE7	2022-07-30	2022-07-31	task 5 2 2 1	task 5 2 2 1	2022-06-20 09:42:00.581481	Default_task	4
55	0	5	Completed	TUID1306C	2022-08-10	2022-08-12	task 4 1 3 1	task 4 1 3 1	2022-06-20 09:42:00.581481	Default_task	5
6	0	2	Scheduled	TUID14235	2022-06-21	2022-06-23	task 1 2 1 1	task 1 2 1 1	2022-06-20 09:42:00.581481	Default_task	2
18	1	8	Completed	TUID1FD11	2022-07-23	2022-07-26	task 3 2 2 1	task 3 2 2 1	2022-06-20 09:42:00.581481	Default_task	4
62	3	2	In_progress	TUID142A5	2022-09-01	2022-09-03	task 1 1 4 1	task 1 1 4 1	2022-06-20 09:42:00.581481	Default_task	7
11	0	2	In_progress	TUID17995	2022-07-01	2022-07-03	task 1 1 2 1	task 1 1 2 1	2022-06-20 09:42:00.581481	Default_task	3
58	0	5	Completed	TUID156B6	2022-08-19	2022-08-22	task 2 2 3 1	task 2 2 3 1	2022-06-20 09:42:00.581481	Default_task	6
71	0	1	Completed	TUID12AF1	2022-09-29	2022-09-29	task 5 2 4 1	task 5 2 4 1	2022-06-20 09:42:00.581481	Default_task	8
102	0	5	In_progress	TUID10EE1	2022-10-01	2022-10-04	task 1 1 5 1	task 1 1 5 1	2022-06-20 09:42:00.581481	Default_task	9
12	0	3	Scheduled	TUID1AA61	2022-07-04	2022-07-06	task 2 1 2 1	task 2 1 2 1	2022-06-20 09:42:00.581481	Default_task	3
2	0	3	Scheduled	TUID1F5A1	2022-06-05	2022-06-08	task  2 1 1 1	task  2 1 1 1	2022-06-20 09:42:00.581481	Default_task	1
19	2	3	Completed	TUID14DD7	2022-07-27	2022-07-29	task 4 2 2 1	task 4 2 2 1	2022-06-20 09:42:00.581481	Default_task	4
61	0	2	Completed	TUID1D073	2022-08-30	2022-08-31	task 5 2 3 1	task 5 2 3 1	2022-06-20 09:42:00.581481	Default_task	6
9	0	2	Scheduled	TUID1DB7F	2022-06-28	2022-06-29	task 4 2 1 1	task 4 2 1 1	2022-06-20 09:42:00.581481	Default_task	2
15	0	8	Completed	TUID16E75	2022-07-14	2022-06-17	task 5 1 2 1	task 5 1 2 1	2022-06-20 09:42:00.581481	Default_task	3
57	0	2	Completed	TUID1C2B3	2022-08-16	2022-08-18	task 1 2 3 1	task 1 2 3 1	2022-06-20 09:42:00.581481	Default_task	6
68	0	5	Completed	TUID18175	2022-09-20	2022-09-23	task 2 2 4 1	task 2 2 4 1	2022-06-20 09:42:00.581481	Default_task	8
53	0	2	In_progress	TUID1EE5F	2022-08-04	2022-08-06	task 2 1 3 1	task 2 1 3 1	2022-06-20 09:42:00.581481	Default_task	5
59	0	8	Completed	TUID184BB	2022-08-23	2022-08-26	task 3 2 3 1	task 3 2 3 1	2022-06-20 09:42:00.581481	Default_task	6
65	0	2	Completed	TUID18917	2022-09-11	2022-09-13	task 4 1 4 1	task 4 1 4 1	2022-06-20 09:42:00.581481	Default_task	7
66	0	8	Completed	TUID11573	2022-09-14	2022-09-17	task 5 1 4 1	task 5 1 4 1	2022-06-20 09:42:00.581481	Default_task	7
4	0	2	Scheduled	TUID1B3BC	2022-06-13	2022-06-16	task 4 1 1 1	task 4 1 1 1	2022-06-20 09:42:00.581481	Default_task	1
5	0	2	Scheduled	TUID1E635	2022-06-17	2022-06-20	task 5 1 1 1	task 5 1 1 1	2022-06-20 09:42:00.581481	Default_task	1
52	1	2	Failed	TUID10A14	2022-08-01	2022-08-03	task 1 1 3 1	task 1 1 3 1	2022-06-20 09:42:00.581481	Default_task	5
64	1	3	Scheduled	TUID1BB09	2022-09-07	2022-09-10	task 3 1 4 1	task 3 1 4 1	2022-06-20 09:42:00.581481	Default_task	7
\.


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users (id, email, password, username) FROM stdin;
1	meher22@productowner.tn	$2a$10$TzdhYLkSTDUlx7eTaKs0qesEWzuB5U9MbOdicmqMCUG4eEgv1mSuC	meher22
52	scrum22@scrumm.tn	$2a$10$/nl5I1RTbTJMwtZghwPjT.gzZXgUKKBtEMXtJA1eUzcDjyrx.TOYq	scrumm22
53	developer22@developer.tn	$2a$10$FGMO9VDI/1g/.TjqTdGTwO09J1llZRVTmyx2WRsZVh2xE56c81Vy6	developer
102	test21@productowner.tn	$2a$10$/LVndwIg2l3tSV9rHIHWQe4mojIn0C13p7LmLnPXQo2bj3AHR4OJ2	test21
152	testauto@scrumm.tn	$2a$10$SXkCq9MeoO4tTzJIfpIjOuqR8ogrFKUwYCdkZrbDMFtcf9C0NLISa	testauto
202	testauto5@scrumm.tn	$2a$10$ZYJCgCJluT2tvNvVIcUY3eD4mmP5jpKCsJSjrmXrh3C0xFMr9papK	testauto5
\.


--
-- Data for Name: users_roles; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users_roles (user_id, role_id) FROM stdin;
1	1
52	2
53	3
102	1
152	2
202	2
\.


--
-- Data for Name: workedl_sprints; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.workedl_sprints (sprint_id, workedl_array) FROM stdin;
1	23
1	23
1	23
1	20
1	20
1	20
1	18
1	18
1	17
1	{"(23,23,23,20,20,20,18,18,17)"}
\.


--
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.hibernate_sequence', 3, true);


--
-- Name: jcoverage_generator; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.jcoverage_generator', 1, false);


--
-- Name: project_generator; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.project_generator', 51, true);


--
-- Name: sprint_generator; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.sprint_generator', 51, true);


--
-- Name: story_generator; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.story_generator', 51, true);


--
-- Name: task_generator; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.task_generator', 51, true);


--
-- Name: user_generator; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.user_generator', 251, true);


--
-- Name: jcoverage jcoverage_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.jcoverage
    ADD CONSTRAINT jcoverage_pkey PRIMARY KEY (id);


--
-- Name: projets projets_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.projets
    ADD CONSTRAINT projets_pkey PRIMARY KEY (id);


--
-- Name: roles roles_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.roles
    ADD CONSTRAINT roles_pkey PRIMARY KEY (id);


--
-- Name: sprints sprints_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.sprints
    ADD CONSTRAINT sprints_pkey PRIMARY KEY (id);


--
-- Name: story story_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.story
    ADD CONSTRAINT story_pkey PRIMARY KEY (id);


--
-- Name: tasks tasks_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tasks
    ADD CONSTRAINT tasks_pkey PRIMARY KEY (id);


--
-- Name: users uk6dotkott2kjsp8vw4d0m25fb7; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT uk6dotkott2kjsp8vw4d0m25fb7 UNIQUE (email);


--
-- Name: tasks ukd2tqpjgbj1wco4sk5katf0kr5; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tasks
    ADD CONSTRAINT ukd2tqpjgbj1wco4sk5katf0kr5 UNIQUE (t_reference);


--
-- Name: story ukiknbwta104re0t2q0s1vmi13e; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.story
    ADD CONSTRAINT ukiknbwta104re0t2q0s1vmi13e UNIQUE (st_reference);


--
-- Name: projets ukjxj9ica9v4r26s4oa9prjyxwq; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.projets
    ADD CONSTRAINT ukjxj9ica9v4r26s4oa9prjyxwq UNIQUE (p_reference);


--
-- Name: sprints ukmwl4poarb5vvhhqox2srryr3c; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.sprints
    ADD CONSTRAINT ukmwl4poarb5vvhhqox2srryr3c UNIQUE (s_reference);


--
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- Name: users_roles users_roles_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users_roles
    ADD CONSTRAINT users_roles_pkey PRIMARY KEY (user_id, role_id);


--
-- Name: users_roles fk2o0jvgh89lemvvo17cbqvdxaa; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users_roles
    ADD CONSTRAINT fk2o0jvgh89lemvvo17cbqvdxaa FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- Name: sprints fk64xg7jbm1wsugc9g0vbyw0b45; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.sprints
    ADD CONSTRAINT fk64xg7jbm1wsugc9g0vbyw0b45 FOREIGN KEY (projet_id) REFERENCES public.projets(id) ON DELETE CASCADE;


--
-- Name: ideall_sprints fk68kcgkuwus15n9crgew4chb2x; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ideall_sprints
    ADD CONSTRAINT fk68kcgkuwus15n9crgew4chb2x FOREIGN KEY (sprint_id) REFERENCES public.sprints(id);


--
-- Name: story fk7cya23djg503d72wl6ps6t7yx; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.story
    ADD CONSTRAINT fk7cya23djg503d72wl6ps6t7yx FOREIGN KEY (sprint_id) REFERENCES public.sprints(id) ON DELETE CASCADE;


--
-- Name: projet_percentage_spc fk9hk70plgmff6805291ipytbdj; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.projet_percentage_spc
    ADD CONSTRAINT fk9hk70plgmff6805291ipytbdj FOREIGN KEY (projet_id) REFERENCES public.projets(id);


--
-- Name: projet_more_sp fkc8wwxbheqmpqkoe3kvo6nqe1g; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.projet_more_sp
    ADD CONSTRAINT fkc8wwxbheqmpqkoe3kvo6nqe1g FOREIGN KEY (projet_id) REFERENCES public.projets(id);


--
-- Name: tasks fkf87etkkjurkmll09duygb67u; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tasks
    ADD CONSTRAINT fkf87etkkjurkmll09duygb67u FOREIGN KEY (story_id) REFERENCES public.story(id) ON DELETE CASCADE;


--
-- Name: users_roles fkj6m8fwv7oqv74fcehir1a9ffy; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users_roles
    ADD CONSTRAINT fkj6m8fwv7oqv74fcehir1a9ffy FOREIGN KEY (role_id) REFERENCES public.roles(id);


--
-- Name: workedl_sprints fkko1x787t2cdf7unwefog3yd7d; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.workedl_sprints
    ADD CONSTRAINT fkko1x787t2cdf7unwefog3yd7d FOREIGN KEY (sprint_id) REFERENCES public.sprints(id);


--
-- Name: days_sprints fkpkw1mcie4i1qdur0nr11hon1l; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.days_sprints
    ADD CONSTRAINT fkpkw1mcie4i1qdur0nr11hon1l FOREIGN KEY (sprint_id) REFERENCES public.sprints(id);


--
-- Name: projet_sp_completed fkq9t3fgq3wrd0lyvhsekc1dorl; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.projet_sp_completed
    ADD CONSTRAINT fkq9t3fgq3wrd0lyvhsekc1dorl FOREIGN KEY (projet_id) REFERENCES public.projets(id);


--
-- Name: projet_sp_commitment fksg9fpflb15fxc0gohddpo2pxk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.projet_sp_commitment
    ADD CONSTRAINT fksg9fpflb15fxc0gohddpo2pxk FOREIGN KEY (projet_id) REFERENCES public.projets(id);


--
-- Name: TABLE days_sprints; Type: ACL; Schema: public; Owner: postgres
--

GRANT ALL ON TABLE public.days_sprints TO myuser;


--
-- Name: TABLE ideall_sprints; Type: ACL; Schema: public; Owner: postgres
--

GRANT ALL ON TABLE public.ideall_sprints TO myuser;


--
-- Name: TABLE jcoverage; Type: ACL; Schema: public; Owner: postgres
--

GRANT ALL ON TABLE public.jcoverage TO myuser;


--
-- Name: TABLE projet_more_sp; Type: ACL; Schema: public; Owner: postgres
--

GRANT ALL ON TABLE public.projet_more_sp TO myuser;


--
-- Name: TABLE projet_percentage_spc; Type: ACL; Schema: public; Owner: postgres
--

GRANT ALL ON TABLE public.projet_percentage_spc TO myuser;


--
-- Name: TABLE projet_sp_commitment; Type: ACL; Schema: public; Owner: postgres
--

GRANT ALL ON TABLE public.projet_sp_commitment TO myuser;


--
-- Name: TABLE projet_sp_completed; Type: ACL; Schema: public; Owner: postgres
--

GRANT ALL ON TABLE public.projet_sp_completed TO myuser;


--
-- Name: TABLE projets; Type: ACL; Schema: public; Owner: postgres
--

GRANT ALL ON TABLE public.projets TO myuser;


--
-- Name: TABLE roles; Type: ACL; Schema: public; Owner: postgres
--

GRANT ALL ON TABLE public.roles TO myuser;


------
-- Name: TABLE sprints; Type: ACL; Schema: public; Owner: postgres
-----

GRANT ALL ON TABLE public.sprints TO myuser;


--
-- Name: TABLE story; Type: ACL; Schema: public; Owner: postgres
--

GRANT ALL ON TABLE public.story TO myuser;


--
-- Name: TABLE tasks; Type: ACL; Schema: public; Owner: postgres
--

GRANT ALL ON TABLE public.tasks TO myuser;


--
-- Name: TABLE users; Type: ACL; Schema: public; Owner: postgres
--

GRANT ALL ON TABLE public.users TO myuser;


--
-- Name: TABLE users_roles; Type: ACL; Schema: public; Owner: postgres
--

GRANT ALL ON TABLE public.users_roles TO myuser;


--
-- Name: TABLE workedl_sprints; Type: ACL; Schema: public; Owner: postgres
--

GRANT ALL ON TABLE public.workedl_sprints TO myuser;


--
-- PostgreSQL database dump complete
--

