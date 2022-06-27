import 'package:sqflite/sqflite.dart';

import '../model/shopped.dart';

class ShoppedDao {

  static Future<List<Shopped>> getAll(Database db) async {
    final List<Map<String, dynamic>> maps = await db.query('shopped');
    return List.generate(maps.length, (i) {
      return Shopped(
          user: maps[i]['user'],
          shopElement: maps[i]['shop_element'],
          boughtOn: maps[i]['bought_on']
      );
    });
  }
  
  static Future<List<Shopped>> getAllByUser(Database db, int userId) async {
    final List<Map<String, dynamic>> maps = await db.query(
        'shopped',
        columns: ['*'],
        where: 'user = ?',
        whereArgs: [userId]
    );
    return List.generate(maps.length, (i) {
      return Shopped(
          user: maps[i]['user'],
          shopElement: maps[i]['shop_element'],
          boughtOn: maps[i]['bought_on']
      );
    });
  }

  static Future<void> insert(Database db, Shopped shopped) async {
    await db.insert('shopped', shopped.toMap());
  }

  static Future<void> insertAll(Database db, List<Shopped> shoppedList) async {
    for(Shopped shopped in shoppedList) {
      await db.insert('shopped', shopped.toMap());
    }
  }

  static Future<void> update(Database db, Shopped shopped) async {
    await db.update('shopped', shopped.toMap(), where: 'user = ? AND shopElement = ?', whereArgs: [shopped.user, shopped.shopElement]);
  }

  static Future<void> delete(Database db, int user, String shopElement) async {
    await db.delete('shopped', where: 'element_name = ?', whereArgs: [user, shopElement]);
  }

}