import 'package:sqflite/sqflite.dart';

import '../model/story.dart';

class StoryDao {

  static Future<List<Story>> getAll(Database db) async {
    final List<Map<String, dynamic>> maps = await db.query('story');
    return List.generate(maps.length, (i) {
      return Story(id: maps[i]['id'], storyName: maps[i]['story_name'], createdOn: maps[i]['createdOn'], user: maps[i]['id']);
    });
  }

  static Future<void> insertUser(Database db, Story story) async {
    await db.insert('story', story.toMap());
  }

  static Future<void> updateUser(Database db, Story story) async {
    await db.update('story', story.toMap(), where: 'id = ?', whereArgs: [story.id]);
  }

  static Future<void> deleteUser(Database db, int id) async {
    await db.delete('story', where: 'id = ?', whereArgs: [id]);
  }

}