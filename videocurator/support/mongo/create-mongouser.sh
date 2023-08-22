#!/bin/sh
mongosh youtube-mongodb --eval "db.createUser({ user: 'user1', pwd: 'user1', roles: [{ role: 'dbOwner', db: 'youtube-mongodb' }] })"