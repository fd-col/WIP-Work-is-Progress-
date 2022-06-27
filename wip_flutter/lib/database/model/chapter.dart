class Chapter {

  final int id;
  final String chapterName;
  final int time;
  final String createdOn;
  final String avatar;
  final int story;

  Chapter({
    required this.id,
    required this.chapterName,
    required this.time,
    required this.createdOn,
    required this.avatar,
    required this.story
  });

  Map<String, dynamic> toMap() {
    return {
      'id': id,
      'chapter_name': chapterName,
      'time': time,
      'created_on': createdOn,
      'avatar': avatar,
      'story': story
    };
  }

}