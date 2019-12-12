INSERT INTO roles values (1,"USER");
INSERT INTO roles values (2,"ADMIN");


INSERT INTO users (name,password) values ("admin","$2y$12$LBAW/A71ySNnfzcHhxaR1OjbZFCTYSPFlvCPHfhb764s.piYtHD8K");# admin/admin
INSERT INTO user_roles values (1,2);





INSERT INTO users (name,password) values ("merlin","$2y$12$kypzoeJ6pMeBcKIlr4st9.t.6bCkAcNyAcG5K8MyX/TC8asYLrNLm"); #merlin/merlin
INSERT INTO user_roles values (2,1);