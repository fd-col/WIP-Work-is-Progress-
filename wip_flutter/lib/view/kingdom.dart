import 'package:flutter/material.dart';
import 'package:sqflite/sqflite.dart';
import 'package:wip_flutter/database/dao/story_dao.dart';
import 'package:wip_flutter/database/model/story.dart';
import 'package:wip_flutter/view/wip_menu.dart';

import '../database/wip_db.dart';

class Kingdom extends StatefulWidget {
  const Kingdom({Key? key, required this.title}) : super(key: key);

  final String title;

  @override
  State<Kingdom> createState() => _KingdomState();

}

class _KingdomState extends State<Kingdom> {

  List<String> storyNames = <String>[];

  @override
  void initState() {
    super.initState();
    getStories();
  }

  void getStories() async {

    Database wipDb = await WIPDb.getDb();

    List<Story> stories = await StoryDao.getAllByUser(wipDb, 1);

    setState(() {
      for(Story story in stories) {
        storyNames.add(story.storyName);
      }
    });

  }

  Widget createListViewChild(int index) {

    MainAxisAlignment alignment = MainAxisAlignment.start;

    if(index%2 != 0) {
      alignment = MainAxisAlignment.end;
    }

    return Row(
      mainAxisAlignment: alignment,
      children: [
        Container(
          height: 70,
          width: 170,
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
              const Text(
                'Capitoli: 1',
                textAlign: TextAlign.center,
                style: TextStyle(
                  color: Color.fromARGB(255, 2, 119, 189),
                  fontFamily: 'PressStart2P',
                  fontSize: 13,
                ),
              )
            ],
          ),
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
        padding: const EdgeInsets.only(top: 70, bottom: 30),
        child: Center(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            children: [
              SizedBox(
                height: 600,
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