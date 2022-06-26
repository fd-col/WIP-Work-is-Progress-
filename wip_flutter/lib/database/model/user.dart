class User {

  final int id;

  User({required this.id});

  Map<String, dynamic> toMap() {
    return {'id': id};
  }

}