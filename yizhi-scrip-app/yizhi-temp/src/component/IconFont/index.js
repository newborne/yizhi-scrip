import React from 'react';
import { Text } from 'react-native';
import IconMap from '@src/res/font/IconMap';

function Index(props) {
  return (
    <Text
      onPress={props.onPress}
      style={{ fontFamily: 'iconfont', ...props.style }}
    >
      {IconMap[props.name]}
    </Text>
  );
}
export default Index;
