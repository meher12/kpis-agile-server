--
-- PostgreSQL database dump
--
\c postgres;

\copy projets FROM '/datasqlkpiagile/projets.csv' DELIMITER ',' ;

\copy sprints  FROM '/datasqlkpiagile/sprints.csv' DELIMITER ',' ;

\copy story  FROM '/datasqlkpiagile/story.csv' DELIMITER ',';

\copy tasks  FROM '/datasqlkpiagile/task.csv' DELIMITER ',';

\copy users  FROM '/datasqlkpiagile/user.csv' DELIMITER ',';

\copy users_roles  FROM '/datasqlkpiagile/userrole.csv' DELIMITER ',';

\copy workedl_sprints  FROM '/datasqlkpiagile/workedl_sprints.csv' DELIMITER ',';

\copy days_sprints  FROM '/datasqlkpiagile/days_sprints.csv' DELIMITER ',';

\copy ideall_sprints  FROM 'datasqlkpiagile/ideall_sprints.csv' DELIMITER ',';

\copy projet_more_sp  FROM '/datasqlkpiagile/projet_more_sp.csv' DELIMITER ',';

\copy projet_percentage_spc  FROM '/datasqlkpiagile/projet_percentage_spc.csv' DELIMITER ',';

\copy projet_sp_commitment  FROM '/datasqlkpiagile/projet_sp_commitment.csv' DELIMITER ',';

\copy projet_sp_completed  FROM '/datasqlkpiagile/projet_sp_completed.csv' DELIMITER ',';