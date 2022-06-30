import 'package:flutter/material.dart';
import 'package:sqflite/sqflite.dart';
import 'package:wip_flutter/arguments/story_detail_arguments.dart';
import 'package:wip_flutter/database/dao/chapter_dao.dart';
import 'package:wip_flutter/database/dao/story_dao.dart';
import 'package:wip_flutter/database/model/story.dart';
import 'package:wip_flutter/view/wip_menu.dart';

import '../database/model/chapter.dart';
import '../database/wip_db.dart';

class Kingdom extends StatefulWidget {
  const Kingdom({Key? key, required this.title}) : super(key: key);

  final String title;

  @override
  State<Kingdom> createState() => _KingdomState();

}

class _KingdomState extends State<Kingdom> {

  List<String> storyNames = <String>[];

  Map<int, String> storyIdName = <int, String>{};

  Map<String, List<Chapter>> storyChapters = <String, List<Chapter>>{};

  @override
  void initState() {
    super.initState();
    getStoriesAndChapters();
  }

  void getStoriesAndChapters() async {

    Database wipDb = await WIPDb.getDb();

    List<Story> stories = await StoryDao.getAllByUser(wipDb, 1);

    List<Chapter> chapters = <Chapter>[];

    for(Story story in stories) {
      chapters.addAll(await ChapterDao.getAllByStory(wipDb, story.id));
    }

    setState(() {
      for(Story story in stories) {
        storyNames.add(story.storyName);
        storyIdName.putIfAbsent(story.id, () => story.storyName);
      }

      for(Chapter chapter in chapters) {
        String storyName = storyIdName[chapter.story]!;

        if(storyChapters.keys.contains(storyName)) {
          storyChapters[storyName]!.add(chapter);
        } else {
          storyChapters.putIfAbsent(storyName, () => [chapter]);
        }
      }
    });

  }

  Widget createListViewChild(int index) {

    MainAxisAlignment alignment = MainAxisAlignment.start;

    if(index%2 != 0) {
      alignment = MainAxisAlignment.end;
    }

    int storyId = 0;
    String storyName = '';

    for(MapEntry<int, String> entry in storyIdName.entries) {
      if(entry.value == storyNames[index]) {
        storyId = entry.key;
        storyName = entry.value;
        break;
      }
    }

    return Row(
      mainAxisAlignment: alignment,
      children: [
        GestureDetector(
          onTap: () {
            Navigator.pushNamed(
                context,
                '/story-detail',
                arguments: StoryDetailArguments(storyId: storyId, storyName: storyName)
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
                  storyNames[index],
                  textAlign: TextAlign.center,
                  style: const TextStyle(
                    color: Color.fromARGB(255, 2, 119, 189),
                    fontFamily: 'PressStart2P',
                    fontSize: 16,
                  ),
                ),
                Text(
                  'Capitoli: ${storyChapters[storyNames[index]]!.length}',
                  textAlign: TextAlign.center,
                  style: const TextStyle(
                    color: Color.fromARGB(255, 2, 119, 189),
                    fontFamily: 'PressStart2P',
                    fontSize: 13,
                  ),
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
              const Text(
                'Regno',
                textAlign: TextAlign.center,
                style: TextStyle(
                  color: Colors.white,
                  fontFamily: 'PressStart2P',
                  fontSize: 28,
                ),
              ),
              SizedBox(
                height: MediaQuery.of(context).size.height * 0.65,
                child: ListView.builder(
                    padding: const EdgeInsets.only(left: 23, right: 23),
                    itemCount: storyNames.length,
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