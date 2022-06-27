class Story {

  final int id;
  final String storyName;
  final String createdOn;
  final int user;

  Story({
    required this.id,
    required this.storyName,
    required this.createdOn,
    required this.user});

  Map<String, dynamic> toMap() {
    return {
      'id': id,
      'story_name': storyName,
      'created_on': createdOn,
      'user': user
    };
  }

}