//
//  MyKeychain.m
//  Keychain Example
//
//  Created by Ricardo Champa on 29/08/15.
//  Copyright (c) 2015 Clickmobile. All rights reserved.
//

#import "MyKeychain.h"

@implementation MyKeychain


-(id)initWithSecret:(NSString *)secret server:(NSString*)server
{
    self = [super init];
    if (self) {
        self.secret = secret;
        self.server = server;
    }
    return self;
}

-(NSMutableDictionary*) getQuery
{
    //Let's create an empty mutable dictionary:
    NSMutableDictionary *query = [NSMutableDictionary dictionary];
    //Populate it with the data and the attributes we want to use.
    query[(__bridge id)kSecClass] = (__bridge id)kSecClassInternetPassword; // We specify what kind of keychain item this is.
    query[(__bridge id)kSecAttrAccessible] = (__bridge id)kSecAttrAccessibleWhenUnlocked; // This item can only be accessed when the user unlocks the device.
    query[(__bridge id)kSecAttrServer] = self.server;
    query[(__bridge id)kSecAttrAccount] = self.secret;
    
    return query;
}

-(BOOL) exists:(NSMutableDictionary*) query
{
    if(SecItemCopyMatching((__bridge CFDictionaryRef)query, NULL) == noErr)
    {
        return YES;
    }
    
    return NO;
}

-(NSDictionary*) getData
{
    NSMutableDictionary *query = [self getQuery];
    query[(__bridge id)kSecReturnData] = (__bridge id)kCFBooleanTrue;
    query[(__bridge id)kSecReturnAttributes] = (__bridge id)kCFBooleanTrue;
    
    
    CFDictionaryRef result = nil;
    
    OSStatus sts = SecItemCopyMatching((__bridge CFDictionaryRef)query, (CFTypeRef *)&result);
    
    NSLog(@"Error Code: %d", (int)sts);
    
    if(sts == noErr)
    {
        NSDictionary *resultDict = (__bridge_transfer NSDictionary *)result;
        NSData *data = resultDict[(__bridge id)kSecValueData];
        NSDictionary *my_sensitive_data = [NSKeyedUnarchiver unarchiveObjectWithData:data];
        return my_sensitive_data;
        
    }else
    {
        return nil;
    }
}

-(BOOL) saveData:(NSDictionary*)my_sensitive_data
{
    NSMutableDictionary *query = [self getQuery];
    
    //Check if this keychain item already exists.
    
    if([self exists:query])
    {
        //You should use update
        return NO;
    }
    else
    {
        NSData *serializedDictionary = [NSKeyedArchiver archivedDataWithRootObject:my_sensitive_data];
        query[(__bridge id)kSecValueData] = serializedDictionary; //Our password
        
        OSStatus sts = SecItemAdd((__bridge CFDictionaryRef)query, NULL);
        NSLog(@"Error Code: %d", (int)sts);
        return ((int)sts)==0;
    }
}

-(BOOL) saveOrUpdateData:(NSDictionary*)my_sensitive_data
{
    NSMutableDictionary *query = [self getQuery];
    
    //Check if this keychain item already exists.
    
    if([self exists:query])
    {
        return [self updateData:my_sensitive_data];
    }
    else
    {
        NSData *serializedDictionary = [NSKeyedArchiver archivedDataWithRootObject:my_sensitive_data];
        query[(__bridge id)kSecValueData] = serializedDictionary; //Our sensitive data
        
        OSStatus sts = SecItemAdd((__bridge CFDictionaryRef)query, NULL);
        NSLog(@"Error Code: %d", (int)sts);
        return ((int)sts)==0;
    }
}

-(BOOL) updateData:(NSDictionary*)my_sensitive_data
{
    NSMutableDictionary *query = [self getQuery];
    
    NSData *serializedDictionary = [NSKeyedArchiver archivedDataWithRootObject:my_sensitive_data];
    
    NSMutableDictionary *attributesToUpdate = [NSMutableDictionary dictionary];
    attributesToUpdate[(__bridge id)kSecValueData] = serializedDictionary;
    
    OSStatus sts = SecItemUpdate((__bridge CFDictionaryRef)query, (__bridge CFDictionaryRef)attributesToUpdate);
    NSLog(@"Error Code: %d", (int)sts);
    
    return ((int)sts)==0;
    
}

-(BOOL) deleteData
{
    NSMutableDictionary *query = [self getQuery];
    
    if(SecItemCopyMatching((__bridge CFDictionaryRef)query, NULL) == noErr)
    {
        OSStatus sts = SecItemDelete((__bridge CFDictionaryRef)query);
        NSLog(@"Error Code: %d", (int)sts);
        return ((int)sts)==0;
    }
    return NO;
}


@end
