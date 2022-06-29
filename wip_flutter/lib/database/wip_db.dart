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

    StoryDao.insert(db,
        Story(id: 1, storyName: 'Matematica', createdOn: '2022-06-26 12:00:12', user: 1)
    );

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