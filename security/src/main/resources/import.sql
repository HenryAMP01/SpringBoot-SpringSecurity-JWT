INSERT INTO authority(authority_code, create_at, name, update_at) VALUES ('COD1', NOW(), 'ADMIN',NOW())
INSERT INTO authority(authority_code, create_at, name, update_at) VALUES ('COD2', NOW(), 'USER',NOW())

INSERT INTO user(create_at, password, update_at, username) VALUES (NOW(), '{bcrypt}$2a$10$N.gWvZUSyvh01sr2.kpX9uvUjAKMEHWfkCb4n/sbeza4uJLpLLTri', NOW(), 'user1')
INSERT INTO user(create_at, password, update_at, username) VALUES (NOW(), '{bcrypt}$2a$10$N.gWvZUSyvh01sr2.kpX9uvUjAKMEHWfkCb4n/sbeza4uJLpLLTri', NOW(), 'user2')

INSERT INTO user_authority(authority_id, user_id) VALUES(1,1)
INSERT INTO user_authority(authority_id, user_id) VALUES(2,1)
INSERT INTO user_authority(authority_id, user_id) VALUES(2,2)
