//
//  ViewController.m
//  Keychain Example
//
//  Created by Ricardo Champa on 29/08/15.
//  Copyright (c) 2015 Clickmobile. All rights reserved.
//

#import "ViewController.h"
#import "MyKeychain.h"
//#import <Security.framework>

@interface ViewController ()
{
    MyKeychain* keychain;
}
@end

@implementation ViewController

-(void) viewDidLoad{
    [super viewDidLoad];
}


- (IBAction)saveButtonPressed:(id)sender
{
    
    NSString *username = self.nwUsernameTxtFld.text;
    NSString *password = self.text_dataTxtFld.text;
    NSString *numeric_password = self.numeric_dataTxtFld.text;
    NSString *website = @"http://www.myserver.com";
    
    keychain = [[MyKeychain alloc] initWithSecret:username server:website];
    
    NSDictionary* my_sensitive_data = @{ @"password"     : password,
                                         @"numeric_password" : @([numeric_password intValue]),
                                         // etc.
                                         };
    
    BOOL ok = [keychain saveOrUpdateData:my_sensitive_data];
    
    if(!ok){
        UIAlertView *alert = [[UIAlertView alloc] initWithTitle:NSLocalizedString(@"Error", nil)
                                                        message:NSLocalizedString(@"An error has ocurred.", )
                                                       delegate:nil
                                              cancelButtonTitle:NSLocalizedString(@"OK", nil)
                                              otherButtonTitles:nil];
        [alert show];

    }
    
}

- (IBAction)updateButtonPressed:(id)sender
{
    
    NSString *username = self.nwUsernameTxtFld.text;
    NSString *password = self.text_dataTxtFld.text;
    NSString *numeric_password = self.numeric_dataTxtFld.text;
    NSString *website = @"http://www.myserver.com";
    
    keychain = [[MyKeychain alloc] initWithSecret:username server:website];
    
    NSDictionary* my_sensitive_data = @{ @"password"     : password,
                                         @"numeric_password" : @([numeric_password intValue]),
                                         // etc.
                                         };
    
    BOOL ok = [keychain updateData:my_sensitive_data];
    
    if(!ok){
        UIAlertView *alert = [[UIAlertView alloc] initWithTitle:NSLocalizedString(@"Error", nil)
                                                        message:NSLocalizedString(@"An error has ocurred.", )
                                                       delegate:nil
                                              cancelButtonTitle:NSLocalizedString(@"OK", nil)
                                              otherButtonTitles:nil];
        [alert show];
        
    }
   
}

- (IBAction)getPasswordPressed:(id)sender
{
    
    NSString *username = self.usernameTxtFld.text;
    NSString *website = @"http://www.myserver.com";
    
    keychain = [[MyKeychain alloc] initWithSecret:username server:website];
    
    NSDictionary *my_sensitive_data = [keychain getData];
    
    if(my_sensitive_data==nil){
        UIAlertView *alert = [[UIAlertView alloc] initWithTitle:NSLocalizedString(@"Error", nil)
                                                        message:NSLocalizedString(@"An error has ocurred.", )
                                                       delegate:nil
                                              cancelButtonTitle:NSLocalizedString(@"OK", nil)
                                              otherButtonTitles:nil];
        [alert show];
    }
    else{
        
        NSString* psw = [my_sensitive_data objectForKey:@"password"];
        NSNumber* n_psw = [my_sensitive_data objectForKey:@"numeric_password"];
        
        self.passwordLbl.text = psw;
        self.numericPasswordLbl.text = [n_psw stringValue];

    }
}

- (IBAction)deleteItemPressed:(id)sender
{
    
    NSString *username = self.usernameTxtFld.text;
    NSString *website = @"http://www.myserver.com";
    
    keychain = [[MyKeychain alloc] initWithSecret:username server:website];
    
    BOOL ok = [keychain deleteData];
    
    if(!ok){
        UIAlertView *alert = [[UIAlertView alloc] initWithTitle:NSLocalizedString(@"Error", nil)
                                                        message:NSLocalizedString(@"An error has ocurred.", )
                                                       delegate:nil
                                              cancelButtonTitle:NSLocalizedString(@"OK", nil)
                                              otherButtonTitles:nil];
        [alert show];
        
    }
    
}


@end
