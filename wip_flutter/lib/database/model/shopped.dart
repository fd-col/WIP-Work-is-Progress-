class Shopped {

  final int user;
  final String shopElement;
  final String boughtOn;

  Shopped({
    required this.user,
    required this.shopElement,
    required this.boughtOn
  });

  Map<String, dynamic> toMap() {
    return {
      'user': user,
      'shop_element': shopElement,
      'bought_on': boughtOn
    };
  }

}