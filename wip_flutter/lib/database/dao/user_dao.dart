import 'package:sqflite/sqflite.dart';

import '../model/user.dart';

class UserDao {

  static Future<List<User>> getAll(Database db) async {
    final List<Map<String, dynamic>> maps = await db.query('user');
    return List.generate(maps.length, (i) {
      return User(id: maps[i]['id']);
    });
  }

  static Future<void> insertUser(Database db, User user) async {
    await db.insert('user', user.toMap());
  }

  static Future<void> updateUser(Database db, User user) async {
    await db.update('user', user.toMap(), where: 'id = ?', whereArgs: [user.id]);
  }

  static Future<void> deleteUser(Database db, int id) async {
    await db.delete('user', where: 'id = ?', whereArgs: [id]);
  }

}