class Chapter {

  final int id;
  final String chapterName;
  final int time;
  final String createdOn;
  final int story;

  Chapter({required this.id, required this.chapterName, required this.time, required this.createdOn, required this.story});

  Map<String, dynamic> toMap() {
    return {'id': id, 'storyName': chapterName, 'time': time, 'createdOn': createdOn, 'story': story};
  }

}