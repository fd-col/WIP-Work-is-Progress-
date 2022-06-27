class ShopElement {

  final String elementName;
  final String type;
  final String description;
  final int price;

  ShopElement({
    required this.elementName,
    required this.type,
    required this.description,
    required this.price
  });

  Map<String, dynamic> toMap() {
    return {
      'element_name': elementName,
      'type': type,
      'description': description,
      'price': price
    };
  }

}