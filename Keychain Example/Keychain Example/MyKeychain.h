//
//  MyKeychain.h
//  Keychain Example
//
//  Created by Ricardo Champa on 29/08/15.
//  Copyright (c) 2015 Clickmobile. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface MyKeychain : NSObject

@property (nonatomic, strong) NSString* secret;
@property (nonatomic, strong) NSString* server;

-(id)initWithSecret:(NSString *)secret server:(NSString*)server;
-(BOOL) exists:(NSMutableDictionary*) query;
-(NSDictionary*) getData;
-(BOOL) saveData:(NSDictionary*)my_sensitive_data;
-(BOOL) saveOrUpdateData:(NSDictionary*)my_sensitive_data;
-(BOOL) updateData:(NSDictionary*)my_sensitive_data;
-(BOOL) deleteData;

@end
