import 'package:sqflite/sqflite.dart';

import '../model/chapter.dart';

class ChapterDao {

  static Future<List<Chapter>> getAll(Database db) async {
    final List<Map<String, dynamic>> maps = await db.query('chapter');
    return List.generate(maps.length, (i) {
      return Chapter(
          id: maps[i]['id'],
          chapterName: maps[i]['chapter_name'],
          time: maps[i]['time'],
          createdOn: maps[i]['created_on'],
          avatar: maps[i]['avatar'],
          story: maps[i]['story']
      );
    });
  }

  static Future<List<Chapter>> getAllByStory(Database db, int storyId) async {

    final List<Map<String, dynamic>> maps = await db.query(
        'chapter',
        columns: ['*'],
        where: 'story = ?',
        whereArgs: [storyId],
    );

    return List.generate(maps.length, (i) {
      return Chapter(
          id: maps[i]['id'],
          chapterName: maps[i]['chapter_name'],
          time: maps[i]['time'],
          createdOn: maps[i]['created_on'],
          avatar: maps[i]['avatar'],
          story: maps[i]['story']
      );
    });
  }

  static Future<void> insert(Database db, Chapter chapter) async {
    await db.insert('chapter', chapter.toMap());
  }

  static Future<void> insertAll(Database db, List<Chapter> chapters) async {
    for(Chapter chapter in chapters) {
      await db.insert('chapter', chapter.toMap());
    }
  }

  static Future<void> update(Database db, Chapter chapter) async {
    await db.update('chapter', chapter.toMap(), where: 'id = ?', whereArgs: [chapter.id]);
  }

  static Future<void> delete(Database db, int id) async {
    await db.delete('chapter', where: 'id = ?', whereArgs: [id]);
  }

}