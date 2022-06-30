class User {

  final int id;
  final int studyTime;
  final int maxStudyTime;

  User({
    required this.id,
    required this.studyTime,
    required this.maxStudyTime
  });

  Map<String, dynamic> toMap() {
    return {
      'id': id,
      'study_time': studyTime,
      'max_study_time': maxStudyTime
    };
  }

}