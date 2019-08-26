//
//  AppDelegate.h
//  rudder-ios-client
//
//  Created by Arnab Pal on 26/08/19.
//  Copyright © 2019 Arnab Pal. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <CoreData/CoreData.h>

@interface AppDelegate : UIResponder <UIApplicationDelegate>

@property (strong, nonatomic) UIWindow *window;

@property (readonly, strong) NSPersistentContainer *persistentContainer;

- (void)saveContext;


@end

