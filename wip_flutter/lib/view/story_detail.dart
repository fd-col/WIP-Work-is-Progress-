import 'package:flutter/material.dart';
import 'package:sqflite/sqflite.dart';
import 'package:wip_flutter/arguments/chapter_info_arguments.dart';
import 'package:wip_flutter/database/dao/chapter_dao.dart';
import 'package:wip_flutter/view/wip_menu.dart';

import '../arguments/story_detail_arguments.dart';
import '../database/model/chapter.dart';
import '../database/wip_db.dart';

class StoryDetail extends StatefulWidget {
  const StoryDetail({Key? key, required this.title}) : super(key: key);

  final String title;

  @override
  State<StoryDetail> createState() => _StoryDetailState();

}

class _StoryDetailState extends State<StoryDetail> {

  String imagesPath = 'assets/images/shared/';

  String backButtonPath = 'assets/images/shared/back_button.png';

  late Image backButtonPressed;

  StoryDetailArguments? args;

  List<Chapter> chapters = <Chapter>[];

  @override
  void initState() {
    super.initState();
    backButtonPressed = Image.asset('${imagesPath}back_button_pressed.png');
  }

  @override
  void didChangeDependencies() {
    super.didChangeDependencies();
    precacheImage(backButtonPressed.image, context);
  }

  void setBackButtonPath(String backButtonPath) {
    setState(() {
      this.backButtonPath = imagesPath + backButtonPath;
    });
  }

  void getChapters(int storyId) async {

    Database wipDb = await WIPDb.getDb();

    List<Chapter> futureChapters = await ChapterDao.getAllByStory(wipDb, storyId);

    setState(() {
      chapters = futureChapters;
    });

  }

  String twoDigits(int n) => n.toString().padLeft(2, '0');

  String getTimeFromSeconds(int totalSeconds) {
    int minutes = totalSeconds~/60;
    int seconds = totalSeconds - minutes*60;
    return '${twoDigits(minutes)}:${twoDigits(seconds)}';
  }

  Widget createListViewChild(int index) {

    MainAxisAlignment alignment = MainAxisAlignment.start;

    if(index%2 != 0) {
      alignment = MainAxisAlignment.end;
    }

    return Row(
      mainAxisAlignment: alignment,
      children: [
        GestureDetector(
            onTap: () {
              Navigator.pushNamed(
                  context,
                  '/chapter-info',
                  arguments: ChapterInfoArguments(
                      createdOn: chapters[index].createdOn,
                      time: getTimeFromSeconds(chapters[index].time),
                      studyTime: chapters[index].studyTime,
                      breakTime: chapters[index].breakTime,
                      mode: chapters[index].mode,
                      avatar: chapters[index].avatar)
              );
            },
            child: Container(
              height: 70,
              width: MediaQuery.of(context).size.width * 0.43,
              padding: const EdgeInsets.only(left: 5, right: 5),
              decoration: const BoxDecoration(
                  image: DecorationImage(
                      image: AssetImage('assets/images/shared/rectangular_background.png'),
                      fit: BoxFit.fill
                  )
              ),
              child: Column(
                mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                crossAxisAlignment: CrossAxisAlignment.center,
                children: [
                  Text(
                    chapters[index].chapterName,
                    textAlign: TextAlign.center,
                    style: const TextStyle(
                      color: Color.fromARGB(255, 2, 119, 189),
                      fontFamily: 'PressStart2P',
                      fontSize: 14,
                    ),
                  ),
                  Text(
                    chapters[index].createdOn,
                    textAlign: TextAlign.center,
                    style: const TextStyle(
                      color: Color.fromARGB(255, 2, 119, 189),
                      fontFamily: 'PressStart2P',
                      fontSize: 11,
                    )
                  ),
                  Text(
                    getTimeFromSeconds(chapters[index].time),
                    textAlign: TextAlign.center,
                    style: const TextStyle(
                    color: Color.fromARGB(255, 2, 119, 189),
                    fontFamily: 'PressStart2P',
                    fontSize: 11,
                    )
                  )
                ],
              ),
            )
        )
      ],
    );
  }

  @override
  Widget build(BuildContext context) {

    args ??= ModalRoute
        .of(context)!
        .settings
        .arguments as StoryDetailArguments;

    getChapters(args!.storyId);

    return Scaffold(
      body: Container(
        decoration: const BoxDecoration(
            image: DecorationImage(
                image: AssetImage('assets/images/shared/background.png'),
                fit: BoxFit.fill
            )
        ),
        padding: const EdgeInsets.only(top: 50, bottom: 30),
        child: Center(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            children: [
              Column(
                children: [
                  Container(
                    width: MediaQuery.of(context).size.width - 10,
                    padding: const EdgeInsets.only(left: 5, right: 5),
                    child: Wrap(
                      runAlignment: WrapAlignment.start,
                      spacing: MediaQuery.of(context).size.width * 0.1,
                      children: [
                        GestureDetector(
                            onTapDown: (_){
                              setBackButtonPath('back_button_pressed.png');
                            },
                            onPanDown: (_){
                              setBackButtonPath('back_button_pressed.png');
                            },
                            onTapCancel: (){
                              setBackButtonPath('back_button.png');
                            },
                            onPanEnd: (_){
                              setBackButtonPath('back_button.png');
                            },
                            onTap: () {
                              Navigator.pop(context);
                              setBackButtonPath('back_button.png');
                            },
                            child: Image.asset(backButtonPath, height: 60, width: 60, fit: BoxFit.cover)
                        ),
                        const Text(
                          'Dettagli\nStoria',
                          textAlign: TextAlign.center,
                          style: TextStyle(
                            color: Colors.white,
                            fontFamily: 'PressStart2P',
                            fontSize: 26,
                          ),
                        ),
                      ],
                    )
                  ),
                  Container(
                    margin: const EdgeInsets.only(top: 10, left: 35, right: 35),
                    child: Image.asset('assets/images/home/under_app_name.png'),
                  )
                ],
              ),
              Text(
                args!.storyName,
                textAlign: TextAlign.center,
                style: const TextStyle(
                  color: Colors.white,
                  fontFamily: 'PressStart2P',
                  fontSize: 20,
                ),
              ),
              SizedBox(
                height: MediaQuery.of(context).size.height * 0.57,
                child: ListView.builder(
                    padding: const EdgeInsets.only(left: 23, right: 23),
                    itemCount: chapters.length,
                    itemBuilder: (_, index) {
                      return createListViewChild(index);
                    }
                ),
              ),
              WIPMenu(parentWidget: widget.toString())
            ],
          ),
        ),
      ),
    );
  }
}