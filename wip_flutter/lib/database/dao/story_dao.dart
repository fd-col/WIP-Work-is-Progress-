import 'package:sqflite/sqflite.dart';

import '../model/story.dart';

class StoryDao {

  static Future<List<Story>> getAll(Database db) async {
    final List<Map<String, dynamic>> maps = await db.query('story');
    return List.generate(maps.length, (i) {
      return Story(
          id: maps[i]['id'],
          storyName: maps[i]['story_name'],
          createdOn: maps[i]['created_on'],
          user: maps[i]['user']
      );
    });
  }

  static Future<void> insert(Database db, Story story) async {
    await db.insert('story', story.toMap());
  }

  static Future<void> update(Database db, Story story) async {
    await db.update('story', story.toMap(), where: 'id = ?', whereArgs: [story.id]);
  }

  static Future<void> delete(Database db, int id) async {
    await db.delete('story', where: 'id = ?', whereArgs: [id]);
  }

}