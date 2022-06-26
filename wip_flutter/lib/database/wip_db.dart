import 'dart:async';
import 'package:sqflite/sqflite.dart';
import 'package:path/path.dart';
import 'package:wip_flutter/database/dao/story_dao.dart';
import 'package:wip_flutter/database/dao/user_dao.dart';
import 'package:wip_flutter/database/model/story.dart';

import 'model/user.dart';

class WIPDb {

  static Future<Database> getDb() async {

    var databasesPath = await getDatabasesPath();

    String path = join(databasesPath, 'wip.db');

    return openDatabase(path, version: 1, onCreate: _onCreate);

  }

  static Future _onCreate(Database db, int version) async {

    await db.execute('''
    
      CREATE TABLE user(
        id INT PRIMARY KEY
      )
      
      ''');

    /*
    ,

      CREATE TABLE story(
        id INT PRIMARY KEY,
        story_name VARCHAR(255) NOT NULL,
        created_on CHAR(19) NOT NULL,
        user INT NOT NULL,
        FOREIGN KEY(user) REFERENCES user(id) ON UPDATE CASCADE ON DELETE CASCADE
      ),

     */

    _populate(db);

  }

  static void _populate(Database db) {

    UserDao.insertUser(db, User(id: 1));

    //StoryDao.insertUser(db, Story(id: 1, storyName: "Matematica", createdOn: "2022-06-26 12:00:12", user: 1));

  }

}