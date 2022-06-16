--
-- PostgreSQL database dump
--

-- Dumped from database version 13.1
-- Dumped by pg_dump version 13.1

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
2	39.0
2	37.7
2	36.4
2	35.100002
2	33.800003
2	32.500004
2	31.200005
2	29.900005
2	28.600006
2	27.300007
2	26.000008
2	24.700008
2	23.40001
2	22.10001
2	20.80001
2	19.500011
2	18.200012
2	16.900013
2	15.600013
2	14.300013
2	13.000012
2	11.700012
2	10.400012
2	9.100012
2	7.8000116
2	6.5000114
2	5.2000113
2	3.9000113
2	2.6000113
2	1.3000114
2	{"(39.0,37.7,36.4,35.100002,33.800003,32.500004,31.200005,29.900005,28.600006,27.300007,26.000008,24.700008,23.40001,22.10001,20.80001,19.500011,18.200012,16.900013,15.600013,14.300013,13.000012,11.700012,10.400012,9.100012,7.8000116,6.5000114,5.2000113,3.9000113,2.6000113,1.3000114)"}
3	0.0
3	0.0
3	0.0
3	0.0
3	0.0
3	0.0
3	0.0
3	0.0
3	0.0
3	0.0
3	0.0
3	0.0
3	0.0
3	0.0
3	0.0
3	0.0
3	0.0
3	0.0
3	0.0
3	0.0
3	0.0
3	0.0
3	0.0
3	0.0
3	0.0
3	0.0
3	0.0
3	0.0
3	0.0
3	0.0
3	{"(0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0)"}
4	0.0
4	0.0
4	0.0
4	0.0
4	0.0
4	0.0
4	0.0
4	0.0
4	0.0
4	0.0
4	0.0
4	0.0
4	0.0
4	0.0
4	0.0
4	0.0
4	0.0
4	0.0
4	0.0
4	0.0
4	0.0
4	0.0
4	0.0
4	0.0
4	0.0
4	0.0
4	0.0
4	0.0
4	0.0
4	{"(0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0)"}
5	0.0
5	0.0
5	0.0
5	0.0
5	0.0
5	0.0
5	0.0
5	0.0
5	0.0
5	0.0
5	0.0
5	0.0
5	0.0
5	0.0
5	0.0
5	0.0
5	0.0
5	0.0
5	0.0
5	0.0
5	0.0
5	0.0
5	0.0
5	0.0
5	0.0
5	0.0
5	0.0
5	0.0
5	0.0
5	0.0
5	{"(0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0)"}
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
\.


--
-- Data for Name: projet_percentage_spc; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.projet_percentage_spc (projet_id, percentage_spc) FROM stdin;
\.


--
-- Data for Name: projet_sp_commitment; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.projet_sp_commitment (projet_id, project_sp_commitment) FROM stdin;
\.


--
-- Data for Name: projet_sp_completed; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.projet_sp_completed (projet_id, project_sp_completed) FROM stdin;
\.


--
-- Data for Name: projets; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.projets (id, date_debut, date_fin, description, p_reference, pupdated_date, title, totalsp_commitment, totalsp_completed, totalstorypointsinitiallycounts) FROM stdin;
1	2022-06-01	2022-10-31	description project 1	PUID1CA4E	2022-06-15 17:21:37.873573	project 1	62	0	0
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
1	0	SUID12E67	2022-06-01	2022-06-30	sprint 1 1	sprint 1 1	2022-06-15 17:21:37.806693	23	0	1
2	0	SUID1CD4C	2022-07-01	2022-07-31	sprint 2 1	sprint 2 1	2022-06-15 17:21:37.806693	39	0	1
3	0	SUID1CE23	2022-08-01	2022-08-31	sprint 3 1	sprint 3 1	2022-06-15 17:21:37.806693	0	0	1
4	0	SUID1BE93	2022-09-01	2022-09-30	sprint 4 1	sprint 4 1	2022-06-15 17:21:37.806693	0	0	1
5	0	SUID154C9	2022-10-01	2022-10-31	sprint 5 1	sprint 5 1	2022-06-15 17:21:37.806693	0	0	1
\.


--
-- Data for Name: story; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.story (id, plus_sp, priority, sp_completed, st_reference, description, title, story_point, stupdated_date, sprint_id) FROM stdin;
1	0	1	0	STUID11411	story 1 1 1	story 1 1 1	14	2022-06-15 17:21:37.823644	1
2	0	2	0	STUID1A4A4	story 2 1 1	story 2 1 1	9	2022-06-15 17:21:37.823644	1
3	0	1	0	STUID120C5	story 1 2 1	story 1 2 1	21	2022-06-15 17:21:37.823644	2
4	0	2	0	STUID173B2	story 2 2 1	story 2 2 1	18	2022-06-15 17:21:37.823644	2
5	0	1	0	STUID1FFB7	story 1 3 1	story 1 3 1	0	2022-06-15 17:21:37.823644	3
6	0	2	0	STUID1CE17	story 2 3 1	story 2 3 1	0	2022-06-15 17:21:37.823644	3
7	0	1	0	STUID19DFD	story 1 4 1	story 1 4 1	0	2022-06-15 17:21:37.823644	4
8	0	2	0	STUID12F5A	story 2 4 1	story 2 4 1	0	2022-06-15 17:21:37.823644	4
9	0	1	0	STUID199C9	story 1 5 1	story 1 5 1	0	2022-06-15 17:21:37.823644	5
10	0	2	0	STUID1ED7D	story 2 5 1	story 2 5 1	0	2022-06-15 17:21:37.823644	5
\.


--
-- Data for Name: tasks; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tasks (id, bugs, estimation, status, t_reference, tdate_debut, tdate_fin, description, title, tsupdated_date, type_task, story_id) FROM stdin;
1	0	2	Scheduled	TUID10CF6	2022-06-01	2022-06-04	task  1 1 1 1	task  1 1 1 1	2022-06-15 17:21:37.856821	Default_task	1
2	0	3	Scheduled	TUID1F5A1	2022-06-05	2022-06-08	task  2 1 1 1	task  2 1 1 1	2022-06-15 17:21:37.856821	Default_task	1
3	0	5	Scheduled	TUID112A3	2022-06-09	2022-06-12	task  3 1 1 1	task  3 1 1 1	2022-06-15 17:21:37.856821	Default_task	1
4	0	2	Scheduled	TUID1B3BC	2022-06-13	2022-06-16	task 4 1 1 1	task 4 1 1 1	2022-06-15 17:21:37.856821	Default_task	1
5	0	2	Scheduled	TUID1E635	2022-06-17	2022-06-20	task 5 1 1 1	task 5 1 1 1	2022-06-15 17:21:37.856821	Default_task	1
6	0	2	Scheduled	TUID14235	2022-06-21	2022-06-23	task 1 2 1 1	task 1 2 1 1	2022-06-15 17:21:37.856821	Default_task	2
7	0	2	Scheduled	TUID172B7	2022-06-24	2022-06-25	task 2 2 1 1	task 2  2 1 1	2022-06-15 17:21:37.856821	Default_task	2
8	0	2	Scheduled	TUID14A72	2022-06-26	2022-06-27	task 3 2 1 1	task 3 2 1 1	2022-06-15 17:21:37.856821	Default_task	2
9	0	2	Scheduled	TUID1DB7F	2022-06-28	2022-06-29	task 4 2 1 1	task 4 2 1 1	2022-06-15 17:21:37.856821	Default_task	2
10	0	1	Scheduled	TUID15B9F	2022-06-30	2022-06-30	task 5 2 1 1	task 5 2 1 1	2022-06-15 17:21:37.856821	Default_task	2
11	0	2	Scheduled	TUID17995	2022-07-01	2022-07-03	task 1 1 2 1	task 1 1 2 1	2022-06-15 17:21:37.856821	Default_task	3
12	0	3	Scheduled	TUID1AA61	2022-07-04	2022-07-06	task 2 1 2 1	task 2 1 2 1	2022-06-15 17:21:37.856821	Default_task	3
13	0	5	Scheduled	TUID162DB	2022-07-06	2022-07-10	task 3 1 2 1	task 3 1 2 1	2022-06-15 17:21:37.856821	Default_task	3
14	0	3	Scheduled	TUID1FC46	2022-07-11	2022-07-13	task 4 1 2 1	task 4 1 2 1	2022-06-15 17:21:37.856821	Default_task	3
15	0	8	Scheduled	TUID16E75	2022-07-14	2022-06-17	task 5 1 2 1	task 5 1 2 1	2022-06-15 17:21:37.856821	Default_task	3
16	0	3	Scheduled	TUID15C07	2022-07-18	2022-07-20	task 1 2 2 1	task 1 2 2 1	2022-06-15 17:21:37.856821	Default_task	4
17	0	2	Scheduled	TUID10F5E	2022-07-21	2022-07-22	task 2 2 2 1	task 2 2 2 1	2022-06-15 17:21:37.856821	Default_task	4
18	0	8	Scheduled	TUID1FD11	2022-07-23	2022-07-26	task 3 2 2 1	task 3 2 2 1	2022-06-15 17:21:37.856821	Default_task	4
19	0	3	Scheduled	TUID14DD7	2022-07-27	2022-07-29	task 4 2 2 1	task 4 2 2 1	2022-06-15 17:21:37.856821	Default_task	4
20	0	2	Scheduled	TUID15FE7	2022-07-30	2022-07-31	task 5 2 2 1	task 5 2 2 1	2022-06-15 17:21:37.856821	Default_task	4
\.


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users (id, email, password, username) FROM stdin;
1	meher22@productowner.tn	$2a$10$TzdhYLkSTDUlx7eTaKs0qesEWzuB5U9MbOdicmqMCUG4eEgv1mSuC	meher22
\.


--
-- Data for Name: users_roles; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users_roles (user_id, role_id) FROM stdin;
1	1
\.


--
-- Data for Name: workedl_sprints; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.workedl_sprints (sprint_id, workedl_array) FROM stdin;
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

SELECT pg_catalog.setval('public.user_generator', 51, true);


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
-- PostgreSQL database dump complete
--

