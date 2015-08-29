//
//  ViewController.h
//  Keychain Example
//
//  Created by Ricardo Champa on 29/08/15.
//  Copyright (c) 2015 Clickmobile. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface ViewController : UIViewController

@property (weak, nonatomic) IBOutlet UITextField *nwUsernameTxtFld;
@property (weak, nonatomic) IBOutlet UITextField *text_dataTxtFld;
@property (weak, nonatomic) IBOutlet UITextField *usernameTxtFld;
@property (weak, nonatomic) IBOutlet UITextField *numeric_dataTxtFld;
@property (weak, nonatomic) IBOutlet UITextView *passwordLbl;
@property (weak, nonatomic) IBOutlet UILabel *numericPasswordLbl;



- (IBAction)saveButtonPressed:(id)sender;
- (IBAction)updateButtonPressed:(id)sender;
- (IBAction)getPasswordPressed:(id)sender;
- (IBAction)deleteItemPressed:(id)sender;

@end

