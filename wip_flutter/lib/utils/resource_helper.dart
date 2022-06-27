String fromShopElementToPath(String shopElement) {

  String imagesPath = 'assets/images/';

  String shopElementPath = '';

  switch(shopElement) {

    case 'venere':
      shopElementPath = 'venere.png';
      break;
    case 'magritte_apple':
      shopElementPath = 'magritte.png';
      break;
    case 'girl_with_pearl_earring':
      shopElementPath = 'ragazza_col_turbante.png';
      break;
    case 'the_scream':
      shopElementPath = 'munch.png';
      break;
    case 'self_portrait':
      shopElementPath = 'van_gogh_self_portrait.png';
      break;
    case 'david':
      shopElementPath = 'david.png';
      break;

  }

  return imagesPath + shopElementPath;

}