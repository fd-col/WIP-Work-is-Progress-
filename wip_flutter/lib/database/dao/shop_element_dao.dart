import 'package:sqflite/sqflite.dart';
import 'package:wip_flutter/database/model/shop_element.dart';

class ShopElementDao {

  static Future<List<ShopElement>> getAll(Database db) async {
    final List<Map<String, dynamic>> maps = await db.query('shop_element');
    return List.generate(maps.length, (i) {
      return ShopElement(
          elementName: maps[i]['element_name'],
          type: maps[i]['type'],
          description: maps[i]['description'],
          price: maps[i]['price']
      );
    });
  }

  static Future<void> insert(Database db, ShopElement shopElement) async {
    await db.insert('shop_element', shopElement.toMap());
  }

  static Future<void> insertAll(Database db, List<ShopElement> shopElements) async {
    for(ShopElement shopElement in shopElements) {
      await db.insert('shop_element', shopElement.toMap());
    }
  }

  static Future<void> update(Database db, ShopElement shopElement) async {
    await db.update('shop_element', shopElement.toMap(), where: 'element_name = ?', whereArgs: [shopElement.elementName]);
  }

  static Future<void> delete(Database db, String elementName) async {
    await db.delete('shop_element', where: 'element_name = ?', whereArgs: [elementName]);
  }

}