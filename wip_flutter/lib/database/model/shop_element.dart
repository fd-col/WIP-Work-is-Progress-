class ShopElement {

  final String elementName;
  final String type;

  ShopElement({
    required this.elementName,
    required this.type,
  });

  Map<String, dynamic> toMap() {
    return {
      'element_name': elementName,
      'type': type
    };
  }

}