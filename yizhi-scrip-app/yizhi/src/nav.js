import React from 'react';
import {NavigationContainer} from '@react-navigation/native';
import {createStackNavigator} from '@react-navigation/stack';
import {inject, observer} from 'mobx-react';
import Login from '@src/page/0_login/login';
import UserInfo from '@src/page/0_login/userInfo';
import Tabbar from '@src/tabbar';
import Demo from '@src/page/demo';
import MaterialType from '@src/page/1_material/materialType';
import News from '@src/page/1_material/news';
import Scrip from '@src/page/1_material/scrip';
import Near from '@src/page/4_users/recommendUser/near';
import Visitor from '@src/page/4_users/recommendUser/visitor';
import TestValue from '@src/page/4_users/recommendUser/testValue';
const Stack = createStackNavigator();
@inject('RootStore')
@observer
class Nav extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      initialRouteName: this.props.RootStore.token ? 'Tabbar' : 'Login',
    };
  }
  render() {
    const {initialRouteName} = this.state;
    return (
      <NavigationContainer>
        <Stack.Navigator
          screenOptions={{headerShown: false}}
          initialRouteName={'Tabbar'}>
          <Stack.Screen name="Demo" component={Demo} />
          <Stack.Screen name="Login" component={Login} />
          <Stack.Screen name="UserInfo" component={UserInfo} />
          <Stack.Screen name="Tabbar" component={Tabbar} />
          <Stack.Screen name="MaterialType" component={MaterialType} />
          <Stack.Screen name="News" component={News} />
          <Stack.Screen name="Scrip" component={Scrip} />
          <Stack.Screen name="Near" component={Near} />
          <Stack.Screen name="Visitor" component={Visitor} />
          <Stack.Screen name="TestValue" component={TestValue} />
        </Stack.Navigator>
      </NavigationContainer>
    );
  }
}
export default Nav;
