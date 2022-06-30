import 'package:sqflite/sqflite.dart';

import '../model/user.dart';

class UserDao {

  static Future<List<User>> getAll(Database db) async {
    final List<Map<String, dynamic>> maps = await db.query('user');
    return List.generate(maps.length, (i) {
      return User(
          id: maps[i]['id'],
          studyTime: maps[i]['study_time'],
          maxStudyTime: maps[i]['max_study_time']
      );
    });
  }

  static Future<void> insert(Database db, User user) async {
    await db.insert('user', user.toMap());
  }

  static Future<void> update(Database db, User user) async {
    await db.update('user', user.toMap(), where: 'id = ?', whereArgs: [user.id]);
  }

  static Future<void> delete(Database db, int id) async {
    await db.delete('user', where: 'id = ?', whereArgs: [id]);
  }

}