class Chapter {

  final int id;
  final String chapterName;
  final int time;
  final String createdOn;
  final int studyTime;
  final int breakTime;
  final String mode;
  final String avatar;
  final int story;

  Chapter({
    required this.id,
    required this.chapterName,
    required this.time,
    required this.createdOn,
    required this.studyTime,
    required this.breakTime,
    required this.mode,
    required this.avatar,
    required this.story
  });

  Map<String, dynamic> toMap() {
    return {
      'id': id,
      'chapter_name': chapterName,
      'time': time,
      'created_on': createdOn,
      'study_time': studyTime,
      'break_time': breakTime,
      'mode': mode,
      'avatar': avatar,
      'story': story
    };
  }

}