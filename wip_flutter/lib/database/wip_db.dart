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

import 'model/user.dart';

class WIPDb {

  static Future<Database> getDb() async {

    var databasesPath = await getDatabasesPath();

    String path = join(databasesPath, 'wip.db');

    return openDatabase(path, version: 2, onCreate: _onCreate);

  }

  static Future _onCreate(Database db, int version) async {

    await db.execute('''
      CREATE TABLE user(
        id INT PRIMARY KEY
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
        avatar VARCHAR(255) NOT NULL,
        story INT,
        FOREIGN KEY(story) REFERENCES story(id) ON UPDATE CASCADE ON DELETE CASCADE
      )
      ''');

    await db.execute('''
      CREATE TABLE shop_element(
        element_name VARCHAR(255) PRIMARY KEY,
        type VARCHAR(10) NOT NULL,
        description VARCHAR(2000) NOT NULL,
        price INT NOT NULL
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

    UserDao.insert(db, User(id: 1));

    List<Story> stories = <Story>[];

    stories.add(Story(id: 1, storyName: 'Aritmetica', createdOn: '2022-06-26 12:00:12', user: 1));
    stories.add(Story(id: 2, storyName: 'Analisi', createdOn: '2022-06-26 13:00:12', user: 1));
    stories.add(Story(id: 3, storyName: 'Algebra', createdOn: '2022-06-26 14:00:12', user: 1));
    stories.add(Story(id: 4, storyName: 'Informatica', createdOn: '2022-06-26 15:00:12', user: 1));
    stories.add(Story(id: 5, storyName: 'Scienze', createdOn: '2022-06-26 16:00:12', user: 1));
    stories.add(Story(id: 6, storyName: 'Storia', createdOn: '2022-06-26 17:00:12', user: 1));
    stories.add(Story(id: 7, storyName: 'Geografia', createdOn: '2022-06-26 18:00:12', user: 1));
    stories.add(Story(id: 8, storyName: 'Astronomia', createdOn: '2022-06-26 19:00:12', user: 1));
    stories.add(Story(id: 9, storyName: 'Fisica', createdOn: '2022-06-26 20:00:12', user: 1));
    stories.add(Story(id: 10, storyName: 'Geometria', createdOn: '2022-06-26 21:00:12', user: 1));
    stories.add(Story(id: 11, storyName: 'Antropologia', createdOn: '2022-06-26 22:00:12', user: 1));
    stories.add(Story(id: 12, storyName: 'Scienze sociali', createdOn: '2022-06-26 23:00:12', user: 1));
    stories.add(Story(id: 13, storyName: 'Psicologia', createdOn: '2022-06-27 00:00:12', user: 1));
    stories.add(Story(id: 14, storyName: 'Filosofia', createdOn: '2022-06-27 01:00:12', user: 1));
    stories.add(Story(id: 15, storyName: 'Arte', createdOn: '2022-06-27 02:00:12', user: 1));

    StoryDao.insertAll(db, stories);

    ChapterDao.insert(db,
        Chapter(id: 1, chapterName: 'Logaritmi', time: 3600, createdOn: '2022-06-26 12:00:12', avatar: 'venere', story: 1)
    );

    List<ShopElement> shopElements = <ShopElement>[];

    shopElements.add(ShopElement(elementName: 'venere', type: 'avatar', description: '', price: 0));
    shopElements.add(ShopElement(elementName: 'magritte_apple', type: 'avatar', description: '', price: 0));
    shopElements.add(ShopElement(elementName: 'girl_with_pearl_earring', type: 'avatar', description: '', price: 0));
    shopElements.add(ShopElement(elementName: 'the_scream', type: 'avatar', description: '', price: 0));
    shopElements.add(ShopElement(elementName: 'self_portrait', type: 'avatar', description: '', price: 0));
    shopElements.add(ShopElement(elementName: 'david', type: 'avatar', description: '', price: 0));

    shopElements.add(ShopElement(elementName: 'the_scream_background', type: 'background', description: '', price: 0));
    shopElements.add(ShopElement(elementName: 'the_persistence_of_memory', type: 'background', description: '', price: 0));
    shopElements.add(ShopElement(elementName: 'hopper_nighthawks', type: 'background', description: '', price: 0));
    shopElements.add(ShopElement(elementName: 'creation_of_adam', type: 'background', description: '', price: 0));
    shopElements.add(ShopElement(elementName: 'lovers', type: 'background', description: '', price: 0));
    shopElements.add(ShopElement(elementName: 'weathfield_with_crows', type: 'background', description: '', price: 0));

    ShopElementDao.insertAll(db, shopElements);

    List<Shopped> shoppedList = <Shopped>[];

    shoppedList.add(Shopped(user: 1, shopElement: 'venere', boughtOn: '2022-06-26 12:00:12'));
    shoppedList.add(Shopped(user: 1, shopElement: 'magritte_apple', boughtOn: '2022-06-26 12:00:12'));
    shoppedList.add(Shopped(user: 1, shopElement: 'girl_with_pearl_earring', boughtOn: '2022-06-26 12:00:12'));
    shoppedList.add(Shopped(user: 1, shopElement: 'the_scream', boughtOn: '2022-06-26 12:00:12'));
    shoppedList.add(Shopped(user: 1, shopElement: 'self_portrait', boughtOn: '2022-06-26 12:00:12'));
    shoppedList.add(Shopped(user: 1, shopElement: 'david', boughtOn: '2022-06-26 12:00:12'));
    shoppedList.add(Shopped(user: 1, shopElement: 'the_scream_background', boughtOn: '2022-06-26 12:00:12'));
    shoppedList.add(Shopped(user: 1, shopElement: 'the_persistence_of_memory', boughtOn: '2022-06-26 12:00:12'));
    shoppedList.add(Shopped(user: 1, shopElement: 'hopper_nighthawks', boughtOn: '2022-06-26 12:00:12'));
    shoppedList.add(Shopped(user: 1, shopElement: 'creation_of_adam', boughtOn: '2022-06-26 12:00:12'));
    shoppedList.add(Shopped(user: 1, shopElement: 'lovers', boughtOn: '2022-06-26 12:00:12'));
    shoppedList.add(Shopped(user: 1, shopElement: 'weathfield_with_crows', boughtOn: '2022-06-26 12:00:12'));

    ShoppedDao.insertAll(db, shoppedList);

  }

}