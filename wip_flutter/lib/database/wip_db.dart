import 'dart:async';
import 'package:sqflite/sqflite.dart';
import 'package:path/path.dart';
import 'package:wip_flutter/database/dao/chapter_dao.dart';
import 'package:wip_flutter/database/dao/shop_element_dao.dart';
import 'package:wip_flutter/database/dao/shopped_dao.dart';
import 'package:wip_flutter/database/dao/story_dao.dart';
import 'package:wip_flutter/database/dao/user_dao.dart';
import 'package:wip_flutter/database/model/chapter.dart';
import 'package:wip_flutter/database/model/shop_element.dart';
import 'package:wip_flutter/database/model/shopped.dart';
import 'package:wip_flutter/database/model/story.dart';

import '../utils/time.dart';
import 'model/user.dart';

class WIPDb {

  static Future<Database> getDb() async {

    var databasesPath = await getDatabasesPath();

    String path = join(databasesPath, 'wip.db');

    return openDatabase(path, version: 4, onCreate: _onCreate);

  }

  static Future _onCreate(Database db, int version) async {

    await db.execute('''
      CREATE TABLE user(
        id INT PRIMARY KEY,
        study_time INT NOT NULL,
        max_study_time INT NOT NULL
      )
      ''');

    await db.execute('''
      CREATE TABLE story(
        id INT PRIMARY KEY,
        story_name VARCHAR(255) NOT NULL,
        created_on CHAR(19) NOT NULL,
        user INT,
        FOREIGN KEY(user) REFERENCES user(id) ON UPDATE CASCADE ON DELETE CASCADE
      )
      ''');

    await db.execute('''
      CREATE TABLE chapter(
        id INT PRIMARY KEY,
        chapter_name VARCHAR(255) NOT NULL,
        time INT NOT NULL,
        created_on CHAR(19) NOT NULL,
        study_time INT NOT NULL,
        break_time INT NOT NULL,
        mode VARCHAR(8),
        avatar VARCHAR(255) NOT NULL,
        story INT,
        FOREIGN KEY(story) REFERENCES story(id) ON UPDATE CASCADE ON DELETE CASCADE
      )
      ''');

    await db.execute('''
      CREATE TABLE shop_element(
        element_name VARCHAR(255) PRIMARY KEY,
        type VARCHAR(10) NOT NULL
      )
      ''');

    await db.execute('''
      CREATE TABLE shopped(
        user INT NOT NULL,
        shop_element VARCHAR(255) NOT NULL,
        bought_on VARCHAR(255) NOT NULL,
        PRIMARY KEY(user, shop_element),
        FOREIGN KEY(user) REFERENCES user(id) ON UPDATE CASCADE ON DELETE CASCADE,
        FOREIGN KEY(shop_element) REFERENCES shop_element(element_name) ON UPDATE CASCADE ON DELETE CASCADE
      )
      ''');

    _populate(db);

  }

  static void _populate(Database db) {

    DateTime dateTime = DateTime.now();

    String nowDateTime = '${dateTime.year}-${twoDigits(dateTime.month)}-${twoDigits(dateTime.day)} '
        '${twoDigits(dateTime.hour)}:${twoDigits(dateTime.minute)}:${twoDigits(dateTime.second)}';

    UserDao.insert(db, User(id: 1, studyTime: 40, maxStudyTime: 60));

    List<Story> stories = <Story>[];

    stories.add(Story(id: 1, storyName: 'Aritmetica', createdOn: nowDateTime, user: 1));
    stories.add(Story(id: 2, storyName: 'Analisi', createdOn: nowDateTime, user: 1));
    stories.add(Story(id: 3, storyName: 'Algebra', createdOn: nowDateTime, user: 1));
    stories.add(Story(id: 4, storyName: 'Informatica', createdOn: nowDateTime, user: 1));
    stories.add(Story(id: 5, storyName: 'Scienze', createdOn: nowDateTime, user: 1));
    stories.add(Story(id: 6, storyName: 'Storia', createdOn: nowDateTime, user: 1));
    stories.add(Story(id: 7, storyName: 'Geografia', createdOn: nowDateTime, user: 1));
    stories.add(Story(id: 8, storyName: 'Astronomia', createdOn: nowDateTime, user: 1));
    stories.add(Story(id: 9, storyName: 'Fisica', createdOn: nowDateTime, user: 1));
    stories.add(Story(id: 10, storyName: 'Geometria', createdOn: nowDateTime, user: 1));
    stories.add(Story(id: 11, storyName: 'Antropologia', createdOn: nowDateTime, user: 1));
    stories.add(Story(id: 12, storyName: 'Scienze sociali', createdOn: nowDateTime, user: 1));
    stories.add(Story(id: 13, storyName: 'Psicologia', createdOn: nowDateTime, user: 1));
    stories.add(Story(id: 14, storyName: 'Filosofia', createdOn: nowDateTime, user: 1));
    stories.add(Story(id: 15, storyName: 'Arte', createdOn: nowDateTime, user: 1));

    StoryDao.insertAll(db, stories);

    List<Chapter> chapters = <Chapter>[];

    chapters.add(Chapter(id: 1, chapterName: 'Capitolo 1', time: 3600, createdOn: nowDateTime, studyTime: 40, breakTime: 20, mode: 'silent', avatar: 'venere', story: 1));
    chapters.add(Chapter(id: 2, chapterName: 'Capitolo 1', time: 3600, createdOn: nowDateTime, studyTime: 30, breakTime: 30, mode: 'hardcore', avatar: 'magritte_apple', story: 2));
    chapters.add(Chapter(id: 3, chapterName: 'Capitolo 1', time: 3600, createdOn: nowDateTime, studyTime: 50, breakTime: 10, mode: 'none', avatar: 'girl_with_pearl_earring', story: 3));
    chapters.add(Chapter(id: 4, chapterName: 'Capitolo 1', time: 3600, createdOn: nowDateTime, studyTime: 20, breakTime: 40, mode: 'silent', avatar: 'the_scream', story: 4));
    chapters.add(Chapter(id: 5, chapterName: 'Capitolo 1', time: 3600, createdOn: nowDateTime, studyTime: 30, breakTime: 30, mode: 'hardcore', avatar: 'self_portrait', story: 5));
    chapters.add(Chapter(id: 6, chapterName: 'Capitolo 1', time: 3600, createdOn: nowDateTime, studyTime: 40, breakTime: 20, mode: 'none', avatar: 'david', story: 6));
    chapters.add(Chapter(id: 7, chapterName: 'Capitolo 1', time: 3600, createdOn: nowDateTime, studyTime: 50, breakTime: 10, mode: 'silent', avatar: 'venere', story: 7));
    chapters.add(Chapter(id: 8, chapterName: 'Capitolo 1', time: 3600, createdOn: nowDateTime, studyTime: 50, breakTime: 10, mode: 'hardcore', avatar: 'magritte_apple', story: 8));
    chapters.add(Chapter(id: 9, chapterName: 'Capitolo 1', time: 3600, createdOn: nowDateTime, studyTime: 40, breakTime: 20, mode: 'silent', avatar: 'girl_with_pearl_earring', story: 9));
    chapters.add(Chapter(id: 10, chapterName: 'Capitolo 1', time: 3600, createdOn: nowDateTime, studyTime: 30, breakTime: 30, mode: 'none', avatar: 'the_scream', story: 10));
    chapters.add(Chapter(id: 11, chapterName: 'Capitolo 1', time: 3600, createdOn: nowDateTime, studyTime: 30, breakTime: 30, mode: 'silent', avatar: 'self_portrait', story: 11));
    chapters.add(Chapter(id: 12, chapterName: 'Capitolo 1', time: 3600, createdOn: nowDateTime, studyTime: 20, breakTime: 40, mode: 'hardcore', avatar: 'david', story: 12));
    chapters.add(Chapter(id: 13, chapterName: 'Capitolo 1', time: 3600, createdOn: nowDateTime, studyTime: 20, breakTime: 40, mode: 'silent', avatar: 'venere', story: 13));
    chapters.add(Chapter(id: 14, chapterName: 'Capitolo 2', time: 3600, createdOn: nowDateTime, studyTime: 30, breakTime: 30, mode: 'none', avatar: 'magritte_apple', story: 13));
    chapters.add(Chapter(id: 15, chapterName: 'Capitolo 1', time: 3600, createdOn: nowDateTime, studyTime: 40, breakTime: 20, mode: 'hardcore', avatar: 'girl_with_pearl_earring', story: 14));
    chapters.add(Chapter(id: 16, chapterName: 'Capitolo 2', time: 3600, createdOn: nowDateTime, studyTime: 50, breakTime: 10, mode: 'silent', avatar: 'the_scream', story: 14));
    chapters.add(Chapter(id: 17, chapterName: 'Capitolo 3', time: 3600, createdOn: nowDateTime, studyTime: 50, breakTime: 10, mode: 'none', avatar: 'self_portrait', story: 14));
    chapters.add(Chapter(id: 18, chapterName: 'Capitolo 1', time: 3600, createdOn: nowDateTime, studyTime: 40, breakTime: 20, mode: 'hardcore', avatar: 'david', story: 15));
    chapters.add(Chapter(id: 19, chapterName: 'Capitolo 2', time: 3600, createdOn: nowDateTime, studyTime: 50, breakTime: 10, mode: 'silent', avatar: 'venere', story: 15));
    chapters.add(Chapter(id: 20, chapterName: 'Capitolo 3', time: 3600, createdOn: nowDateTime, studyTime: 50, breakTime: 10, mode: 'hardcore', avatar: 'venere', story: 15));

    ChapterDao.insertAll(db, chapters);

    List<ShopElement> shopElements = <ShopElement>[];

    shopElements.add(ShopElement(elementName: 'venere', type: 'avatar'));
    shopElements.add(ShopElement(elementName: 'magritte_apple', type: 'avatar'));
    shopElements.add(ShopElement(elementName: 'girl_with_pearl_earring', type: 'avatar'));
    shopElements.add(ShopElement(elementName: 'the_scream', type: 'avatar'));
    shopElements.add(ShopElement(elementName: 'self_portrait', type: 'avatar'));
    shopElements.add(ShopElement(elementName: 'david', type: 'avatar'));

    shopElements.add(ShopElement(elementName: 'the_scream_background', type: 'background'));
    shopElements.add(ShopElement(elementName: 'the_persistence_of_memory', type: 'background'));
    shopElements.add(ShopElement(elementName: 'hopper_nighthawks', type: 'background'));
    shopElements.add(ShopElement(elementName: 'creation_of_adam', type: 'background'));
    shopElements.add(ShopElement(elementName: 'lovers', type: 'background'));
    shopElements.add(ShopElement(elementName: 'weathfield_with_crows', type: 'background'));

    ShopElementDao.insertAll(db, shopElements);

    List<Shopped> shoppedList = <Shopped>[];

    shoppedList.add(Shopped(user: 1, shopElement: 'venere', boughtOn: nowDateTime));
    shoppedList.add(Shopped(user: 1, shopElement: 'magritte_apple', boughtOn: nowDateTime));
    shoppedList.add(Shopped(user: 1, shopElement: 'girl_with_pearl_earring', boughtOn: nowDateTime));
    shoppedList.add(Shopped(user: 1, shopElement: 'the_scream', boughtOn: nowDateTime));
    shoppedList.add(Shopped(user: 1, shopElement: 'self_portrait', boughtOn: nowDateTime));
    shoppedList.add(Shopped(user: 1, shopElement: 'david', boughtOn: nowDateTime));
    shoppedList.add(Shopped(user: 1, shopElement: 'the_scream_background', boughtOn: nowDateTime));
    shoppedList.add(Shopped(user: 1, shopElement: 'the_persistence_of_memory', boughtOn: nowDateTime));
    shoppedList.add(Shopped(user: 1, shopElement: 'hopper_nighthawks', boughtOn: nowDateTime));
    shoppedList.add(Shopped(user: 1, shopElement: 'creation_of_adam', boughtOn: nowDateTime));
    shoppedList.add(Shopped(user: 1, shopElement: 'lovers', boughtOn: nowDateTime));
    shoppedList.add(Shopped(user: 1, shopElement: 'weathfield_with_crows', boughtOn: nowDateTime));

    ShoppedDao.insertAll(db, shoppedList);

  }

}